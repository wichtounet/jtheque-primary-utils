package org.jtheque.primary;

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

import org.jtheque.features.Feature;
import org.jtheque.features.IFeatureService;
import org.jtheque.file.IFileService;
import org.jtheque.file.able.ModuleBackuper;
import org.jtheque.i18n.I18nResource;
import org.jtheque.i18n.ILanguageService;
import org.jtheque.primary.od.able.SimpleData.DataType;
import org.jtheque.primary.utils.DataTypeManager;
import org.jtheque.schemas.ISchemaService;
import org.jtheque.schemas.Schema;
import org.jtheque.utils.bean.Version;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.List;

/**
 * The primary utils. This class give to modules some utilities methods to construct primary module.
 *
 * @author Baptiste Wicht
 */
public final class PrimaryUtils implements IPrimaryUtils, ApplicationContextAware {
    private String primaryImpl;

    private final ISchemaService schemaService;
    private final ILanguageService languageService;
    private final IFileService fileService;
    private final IFeatureService featureService;
	private ApplicationContext applicationContext;

	public PrimaryUtils(ISchemaService schemaService, ILanguageService languageService, IFeatureService featureService, IFileService fileService){
		super();

        this.schemaService = schemaService;
        this.languageService = languageService;
        this.featureService = featureService;
        this.fileService = fileService;
    }

	@Override
    public void prePlug(){
        Schema schema = new PrimaryUtilsSchema();

		schemaService.registerSchema("jtheque-primary-module", schema);

        languageService.registerResource("jtheque-primary-utils", new Version("1.0"),
                I18nResource.fromResource(getClass(), "org/jtheque/primary/i18n/utils_en.properties"),
                I18nResource.fromResource(getClass(), "org/jtheque/primary/i18n/utils_fr.properties"));
	}

	@Override
    public void plug(){
		DataTypeManager.bindDataTypeToKey(PrimaryConstants.BORROWERS, "data.titles.borrower");
		DataTypeManager.bindDataTypeToKey(DataType.COUNTRY.getDataType(), "data.titles.country");
		DataTypeManager.bindDataTypeToKey(DataType.LANGUAGE.getDataType(), "data.titles.language");
		DataTypeManager.bindDataTypeToKey(DataType.TYPE.getDataType(), "data.titles.type");
		DataTypeManager.bindDataTypeToKey(DataType.KIND.getDataType(), "data.titles.kind");
		DataTypeManager.bindDataTypeToKey(DataType.SAGA.getDataType(), "data.titles.saga");

        ModuleBackuper backuper = new PrimaryBackuper();

        fileService.registerBackuper("jtheque-primary-module", backuper);
	}

	@Override
    public void unplug(){
		DataTypeManager.unbindDataType(PrimaryConstants.BORROWERS);
		DataTypeManager.unbindDataType(DataType.COUNTRY.getDataType());
		DataTypeManager.unbindDataType(DataType.LANGUAGE.getDataType());
		DataTypeManager.unbindDataType(DataType.TYPE.getDataType());
		DataTypeManager.unbindDataType(DataType.KIND.getDataType());
		DataTypeManager.unbindDataType(DataType.SAGA.getDataType());
	}

	@Override
    public String getPrimaryImpl(){
		return primaryImpl;
	}

	@Override
    public void setPrimaryImpl(String primaryImpl){
		this.primaryImpl = primaryImpl;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Override
    public void enableMenu(List<Feature> addFeatures, List<Feature> removeFeatures, List<Feature> editFeatures){
        featureService.addMenu("jtheque-primary-module", new PrimaryMenu(addFeatures, removeFeatures, editFeatures, applicationContext));
	}
}