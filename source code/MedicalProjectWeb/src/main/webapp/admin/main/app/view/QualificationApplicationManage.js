/*
 * File: app/view/MyViewport.js
 *
 * This file was generated by Sencha Architect version 2.2.2.
 * http://www.sencha.com/products/architect/
 *
 * This file requires use of the Ext JS 4.2.x library, under independent license.
 * License of Sencha Architect does not include license for Ext JS 4.2.x. For more
 * details see http://www.sencha.com/license or contact license@sencha.com.
 *
 * This file will be auto-generated each and everytime you save your project.
 *
 * Do NOT hand edit this file.
 */

var qualificationApplicationGridStore = Ext.create('Ext.data.Store', {
	extend: 'Ext.data.Store',

	fields: ['id','applyTime','applyUserId','applyUserName','applyUserMoobile','applyUserEmail','yszgzImgRelativePath',
             'reviewUserId','reviewUserName','reviewUserMobile','reviewUserEmail','reviewTime','reviewText','status'],
    autoLoad: true,
    //pageSize: 10,
    proxy: {
        type: 'ajax',
        url : appContext + 'admin/qualificationApplicationManage/listApplication.do',
        actionMethods: { read: 'POST' }  ,	
        pageParam:"page", 
        limitParam:"pageSize",
        reader: {
            type: 'json',
            root: 'data.applications',
            totalProperty: 'data.pager.totalCount'
        }
    }
});


Ext.define('MedicalProject.view.QualificationApplicationManage', {
    extend: 'Ext.container.Container',

    layout: {
        type: 'fit'
    },

    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'container',
                    layout: {
                        align: 'stretch',
                        type: 'vbox'
                    },
                    items: [
                        {
                            xtype: 'form',
                            id:'qualificationApplicationManage_applicationForm',
                            flex: 0,
                            layout: {
                                columns: 5,
                                type: 'table'
                            },
                            bodyPadding: 10,
                            title: '资格申请管理',
                            items: [
                                {
                                    xtype: 'datefield',
                                    margin: '0 5px',
                                    name:'applyTime',
                                    format:'Y-m-d',
                                    fieldLabel: '申请时间'
                                },
                                {
                                    xtype: 'textfield',
                                    margin: '0 5px',
                                    name:'applyUserName',
                                    fieldLabel: '申请人'
                                },
                                {
                                    xtype: 'textfield',
                                    margin: '0 5px',
                                    name:'email',
                                    fieldLabel: '邮箱'
                                },
                                {
                                    xtype: 'combobox',
                                    margin: '0 5px',
                                    fieldLabel: '状态',
                                    name:'status',
                                    mode: 'remote',
                                    valueField: 'statusName',
                                    displayField: 'statusName',
                                    store:Ext.create('Ext.data.SimpleStore',{
                                    	fields: ['statusCode', 'statusName'],
                                    	data : [
                                    		['1','待审核'],
                                    		['2','通过'],
                                    		['3','未通过']
                                    	]
                                    })
                                },
                                {
                                    xtype: 'button',
                                    id:'qualificationApplicationManage_searchBtn',
                                    margin: '0 5px',
                                    width: 80,
                                    text: '搜索'
                                }
                            ]
                        },
                        {
                            xtype: 'gridpanel',
                            id:'qualificationApplicationManage_qualificationApplicationGrid',
                            store: qualificationApplicationGridStore,
                            flex: 1,
                            title: '资格申请列表',
                            columns: [
								{
								    xtype: 'gridcolumn',
								    dataIndex: 'id',
								    text: 'ID'
								},
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'applyTime',
                                    text: '申请时间'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'applyUserName',
                                    text: '申请用户'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'applyUserMobile',
                                    text: '申请用户电话'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'applyUserEmail',
                                    text: '申请用户邮箱'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'reviewUserName',
                                    text: '审查用户'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'reviewUserMobile',
                                    text: '审查用户电话'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'reviewUserEmail',
                                    text: '审查用户邮箱'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    width: 108,
                                    dataIndex: 'reviewTime',
                                    text: '审查时间'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'status',
                                    text: '状态'
                                }
                                
                            ],
                            dockedItems: [
                                {
                                    xtype: 'pagingtoolbar',
                                    dock: 'bottom',
                                    width: 360,
                                    displayInfo: true,
                                    store:qualificationApplicationGridStore
                                },
                                {
                                    xtype: 'toolbar',
                                    dock: 'top',
                                    items: [
										/*{
										    xtype: 'button',
										    id:'qualificationApplicationManage_tbApproveBtn',
										    text: '通过'
										},
										{
										    xtype: 'button',
										    id:'qualificationApplicationManage_tbRejectBtn',
										    text: '不通过'
										},*/
                                        {
                                            xtype: 'button',
                                            id:'qualificationApplicationManage_tbDeleteBtn',
                                            text: '删除'
                                        }
                                    ]
                                }
                            ]
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
        me.bindEvent();
    },
    bindEvent: function(){
    	var me = this;
    	var qualificationApplicationGrid = Ext.getCmp('qualificationApplicationManage_qualificationApplicationGrid');
    	var deleteBtn = Ext.getCmp('qualificationApplicationManage_tbDeleteBtn');
    	var searchBtn = Ext.getCmp('qualificationApplicationManage_searchBtn');
    	
    	deleteBtn.on('click',function(){
    		var selection = qualificationApplicationGrid.getSelectionModel().getSelection();

    		if( selection.length > 0 ){
    			var applicationId = selection[0].data.id;
    			Ext.Ajax.request({
    				url:appContext + 'admin/qualificationApplicationManage/delApplication.do',
    				method:'GET',
    				params:{
    					applicationId: applicationId
    				},
    				success:function(){
    					qualificationApplicationGrid.getStore().reload();
    				}
    			});
    		}else{
    			Ext.Msg.alert('提示','请选择用户');
    		}
    	});
    	
    	searchBtn.on('click',function(){
    		var searchForm = Ext.getCmp('qualificationApplicationManage_applicationForm').getForm();
        	var searchParamObj = searchForm.getValues();
        	
        	searchParamObj.page = 1;
        	qualificationApplicationGrid.store.reload({
        		params:searchParamObj,
        	});
    	});
    	
    	qualificationApplicationGrid.on('itemdblclick',function(){
    	});
    	
    	
    }

});