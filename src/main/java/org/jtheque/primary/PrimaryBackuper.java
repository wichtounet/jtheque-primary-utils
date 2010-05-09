package org.jtheque.primary;

import org.jtheque.file.able.ModuleBackup;
import org.jtheque.file.able.ModuleBackuper;
import org.jtheque.io.Node;
import org.jtheque.persistence.able.IDaoNotes;
import org.jtheque.persistence.impl.DaoNotes;
import org.jtheque.primary.dao.able.IDaoLendings;
import org.jtheque.primary.dao.able.IDaoPersons;
import org.jtheque.primary.dao.able.IDaoSimpleDatas;
import org.jtheque.primary.od.able.Lending;
import org.jtheque.primary.od.able.Person;
import org.jtheque.primary.od.able.PrimaryData;
import org.jtheque.primary.od.able.SimpleData;
import org.jtheque.primary.od.able.SimpleData.DataType;
import org.jtheque.utils.bean.IntDate;
import org.jtheque.utils.bean.Version;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/*
 * This file is part of JTheque.
 *
 * JTheque is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * JTheque is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JTheque.  If not, see <http://www.gnu.org/licenses/>.
 */

public class PrimaryBackuper implements ModuleBackuper {
    private static final String[] DEPENDENCIES = {"jtheque-core-backuper"};

    private static final Version BACKUP_VERSION = new Version("1.0");

    @Resource
    private IDaoPersons daoPersons;

    @Resource
    private IDaoLendings daoLendings;

    @Resource
    private IDaoNotes daoNotes;

    @Resource
    private IDaoSimpleDatas daoCountries;

    @Resource
    private IDaoSimpleDatas daoKinds;

    @Resource
    private IDaoSimpleDatas daoLanguages;

    @Resource
    private IDaoSimpleDatas daoSagas;

    @Resource
    private IDaoSimpleDatas daoTypes;

    @Override
    public String getId() {
        return "jtheque-primary-backuper";
    }

    @Override
    public String[] getDependencies() {
        return DEPENDENCIES;
    }

    @Override
    public ModuleBackup backup() {
        ModuleBackup backup = new ModuleBackup();

        backup.setId(getId());
        backup.setVersion(BACKUP_VERSION);

        Collection<Node> nodes = new ArrayList<Node>(100);

        addPersons(nodes);
        addLendings(nodes);
        addSimpleData(daoCountries, nodes);
        addSimpleData(daoKinds, nodes);
        addSimpleData(daoLanguages, nodes);
        addSimpleData(daoSagas, nodes);
        addSimpleData(daoTypes, nodes);

        backup.setNodes(nodes);

        return backup;
    }

    private void addPersons(Collection<Node> nodes) {
        for(Person person : daoPersons.getPersons()){
            Node node = new Node("person");

            node.addSimpleChildValue("id", person.getId());
            node.addSimpleChildValue("type", person.getType());
            node.addSimpleChildValue("name", person.getName());
            node.addSimpleChildValue("firstname", person.getFirstName());
            node.addSimpleChildValue("email", person.getEmail());
            node.addSimpleChildValue("country", person.getTheCountry().getId());
            node.addSimpleChildValue("impl", person.getPrimaryImpl());
            node.addSimpleChildValue("note", person.getNote().getValue().ordinal());

            nodes.add(node);
        }
    }

    private void addLendings(Collection<Node> nodes) {
        for(Lending lending : daoLendings.getAllLendings()){
            Node node = new Node("lending");

            node.addSimpleChildValue("id", lending.getId());
            node.addSimpleChildValue("date", lending.getDate().intValue());
            node.addSimpleChildValue("person", lending.getThePerson().getId());
            node.addSimpleChildValue("other", lending.getTheOther());
            node.addSimpleChildValue("impl", lending.getPrimaryImpl());

            nodes.add(node);
        }
    }

    private static void addSimpleData(IDaoSimpleDatas dao, Collection<Node> nodes) {
        for(SimpleData data : dao.getSimpleDatas()){
            Node node = new Node("data");

            node.addSimpleChildValue("id", data.getId());
            node.addSimpleChildValue("name", data.getName());
            node.addSimpleChildValue("type", data.getType().toString());

            if(dao.isPrimary()){
                node.addSimpleChildValue("primary", "true");
                node.addSimpleChildValue("impl", ((PrimaryData) data).getPrimaryImpl());
            }

            nodes.add(node);
        }
    }

    @Override
    public void restore(ModuleBackup backup) {
        assert getId().equals(backup.getId()) : "This backuper can only restore its own backups";

        List<Node> nodes = backup.getNodes();

        restoreSimpleDatas(nodes.iterator());
        restorePersons(nodes.iterator());
        restoreLendings(nodes.iterator());
    }

    private void restoreSimpleDatas(Iterator<Node> nodeIterator) {
        Map<DataType, IDaoSimpleDatas> daoCache = new EnumMap<DataType, IDaoSimpleDatas>(DataType.class);

	    daoCache.put(DataType.COUNTRY, daoCountries);
	    daoCache.put(DataType.KIND, daoKinds);
	    daoCache.put(DataType.TYPE, daoTypes);
	    daoCache.put(DataType.SAGA, daoSagas);
	    daoCache.put(DataType.LANGUAGE, daoLanguages);

        while(nodeIterator.hasNext()){
            Node node = nodeIterator.next();

            if("data".equals(node.getName())){
                DataType type = DataType.valueOf(node.getChildValue("type"));

                SimpleData data = daoCache.get(type).create();

                data.getTemporaryContext().setId(node.getChildIntValue("id"));
                data.setName(node.getChildValue("name"));

                if(type.isPrimary()){
                    ((PrimaryData)data).setPrimaryImpl(node.getChildValue("impl"));
                }

                daoCache.get(type).create(data);

                nodeIterator.remove();
            }
        }
    }

    private void restorePersons(Iterator<Node> nodeIterator) {
        while(nodeIterator.hasNext()){
            Node node = nodeIterator.next();

            if("person".equals(node.getName())){
                Person person = daoPersons.create();

                person.getTemporaryContext().setId(node.getChildIntValue("id"));
                person.setEmail(node.getChildValue("email"));
                person.setName(node.getChildValue("name"));
                person.setFirstName(node.getChildValue("firstname"));
                person.setType(node.getChildValue("type"));
                person.setNote(daoNotes.getNote(DaoNotes.NoteType.getEnum(node.getChildIntValue("note"))));
                person.setPrimaryImpl(node.getChildValue("impl"));

                person.setTheCountry(daoCountries.getSimpleDataByTemporaryId(node.getChildIntValue("country")));

                daoPersons.create(person);

                nodeIterator.remove();
            }
        }
    }

    private void restoreLendings(Iterator<Node> nodeIterator) {
        while(nodeIterator.hasNext()){
            Node node = nodeIterator.next();

            if("lending".equals(node.getName())){
                Lending lending = daoLendings.create();

                lending.getTemporaryContext().setId(node.getChildIntValue("id"));
                lending.setDate(new IntDate(node.getChildIntValue("date")));
                lending.setPrimaryImpl(node.getChildValue("impl"));
                lending.setTheOther(node.getChildIntValue("other"));
                lending.setThePerson(daoPersons.getPersonByTemporaryId(node.getChildIntValue("person")));

                nodeIterator.remove();
            }
        }
    }
}