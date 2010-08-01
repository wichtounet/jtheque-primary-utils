package org.jtheque.primary.impl;

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

import org.jtheque.features.able.IFeature;
import org.jtheque.features.able.IFeatureService;
import org.jtheque.file.able.FileService;
import org.jtheque.file.able.ModuleBackuper;
import org.jtheque.primary.able.IPrimaryUtils;
import org.jtheque.primary.able.od.SimpleData.DataType;
import org.jtheque.primary.utils.DataTypeManager;
import org.jtheque.schemas.able.ISchemaService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import java.util.List;

/**
 * The primary utils. This class give to modules some utilities methods to construct primary module.
 *
 * @author Baptiste Wicht
 */
public final class PrimaryUtils implements IPrimaryUtils, ApplicationContextAware {
    private String primaryImpl;

    @Resource
    private ISchemaService schemaService;

    @Resource
    private FileService fileService;

    @Resource
    private IFeatureService featureService;

    private ApplicationContext applicationContext;

    @PostConstruct
    public void plug() {
        DataTypeManager.bindDataTypeToKey(PrimaryConstants.BORROWERS, "data.titles.borrower");
        DataTypeManager.bindDataTypeToKey(DataType.COUNTRY.getDataType(), "data.titles.country");
        DataTypeManager.bindDataTypeToKey(DataType.LANGUAGE.getDataType(), "data.titles.language");
        DataTypeManager.bindDataTypeToKey(DataType.TYPE.getDataType(), "data.titles.type");
        DataTypeManager.bindDataTypeToKey(DataType.KIND.getDataType(), "data.titles.kind");
        DataTypeManager.bindDataTypeToKey(DataType.SAGA.getDataType(), "data.titles.saga");

        ModuleBackuper backuper = new PrimaryBackuper();

        fileService.registerBackuper("jtheque-primary-module", backuper);
    }

    @PreDestroy
    public void unplug() {
        DataTypeManager.unbindDataType(PrimaryConstants.BORROWERS);
        DataTypeManager.unbindDataType(DataType.COUNTRY.getDataType());
        DataTypeManager.unbindDataType(DataType.LANGUAGE.getDataType());
        DataTypeManager.unbindDataType(DataType.TYPE.getDataType());
        DataTypeManager.unbindDataType(DataType.KIND.getDataType());
        DataTypeManager.unbindDataType(DataType.SAGA.getDataType());
    }

    @Override
    public String getPrimaryImpl() {
        return primaryImpl;
    }

    @Override
    public void setPrimaryImpl(String primaryImpl) {
        this.primaryImpl = primaryImpl;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void enableMenu(List<IFeature> addFeatures, List<IFeature> removeFeatures, List<IFeature> editFeatures) {
        featureService.addMenu("jtheque-primary-module", new PrimaryMenu(addFeatures, removeFeatures, editFeatures, applicationContext));
    }
}