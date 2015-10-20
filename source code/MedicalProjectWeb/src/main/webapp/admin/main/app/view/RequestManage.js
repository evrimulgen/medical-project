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

var requestGridStore = Ext.create('Ext.data.Store', {
	extend: 'Ext.data.Store',
    
    fields: ['id','name','mobile','email','userTypeCode','userType','regTime'],
    autoLoad: true,
    //pageSize: 10,
    proxy: {
        type: 'ajax',
        url : appContext + '/admin/userManage/listUser.do',
        actionMethods: { read: 'POST' }  ,	
        pageParam:"page", 
        limitParam:"pageSize",
        reader: {
            type: 'json',
            root: 'data.users',
            totalProperty: 'data.pager.totalCount'
        }
    }
});


Ext.define('MedicalProject.view.RequestManage', {
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
                            id:'requestManage_userSearchForm',
                            flex: 0,
                            layout: {
                                columns: 5,
                                type: 'table'
                            },
                            bodyPadding: 10,
                            title: '请求管理',
                            items: [
                                {
                                    xtype: 'textfield',
                                    margin: '0 5px',
                                    name:'name',
                                    fieldLabel: '姓名'
                                },
                                {
                                    xtype: 'textfield',
                                    margin: '0 5px',
                                    name:'mobile',
                                    fieldLabel: '手机号'
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
                                    fieldLabel: '用户类型',
                                    name:'userType',
                                    mode: 'remote',
                                    valueField: 'typeName',
                                    displayField: 'typeName',
                                    store:Ext.create('Ext.data.SimpleStore',{
                                    	fields: ['typeCode', 'typeName'],
                                    	data : [
                                    		['1','用户'],
                                    		['2','医师'],
                                    		['3','专家'],
                                    		['4','企业用户']
                                    	]
                                    })
                                },
                                {
                                    xtype: 'button',
                                    id:'requestManage_searchBtn',
                                    margin: '0 5px',
                                    width: 80,
                                    text: '搜索'
                                }
                            ]
                        },
                        {
                            xtype: 'gridpanel',
                            id:'requestManage_requestGrid',
                            store: requestGridStore,
                            flex: 1,
                            title: '请求列表',
                            columns: [
								{
								    xtype: 'gridcolumn',
								    dataIndex: 'id',
								    text: 'ID'
								},
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'name',
                                    text: '姓名'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'mobile',
                                    text: '电话'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'email',
                                    text: '邮箱'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    width: 108,
                                    dataIndex: 'userType',
                                    text: '用户类型'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'regTime',
                                    text: '注册时间'
                                }
                                
                            ],
                            dockedItems: [
                                {
                                    xtype: 'pagingtoolbar',
                                    dock: 'bottom',
                                    width: 360,
                                    displayInfo: true,
                                    store:requestGridStore
                                },
                                {
                                    xtype: 'toolbar',
                                    dock: 'top',
                                    items: [
                                        {
                                            xtype: 'button',
                                            id:'requestManage_tbDeleteBtn',
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
        //me.bindEvent();
    },
    bindEvent: function(){
    	var me = this;
    	var requestGrid = Ext.getCmp('requestManage_requestGrid');
    	Ext.getCmp('userManage_tbDeleteUserBtn').on('click',function(){
    		var selection = userGrid.getSelectionModel().getSelection();

    		if( selection.length > 0 ){
    			var userId = selection[0].data.id;
    			Ext.Ajax.request({
    				url:appContext + 'admin/userManage/delUser.do',
    				params:{
    					userId: userId
    				},
    				success:function(){
    					userGrid.getStore().reload();
    				}
    			});
    		}else{
    			Ext.Msg.alert('提示','请选择用户');
    		}
    	});
    	
    	Ext.getCmp('userManage_searchBtn').on('click',function(){
    		var userSearchForm = Ext.getCmp('userManage_userSearchForm').getForm();
        	var searchParamObj = userSearchForm.getValues();
        	
        	var userGrid = Ext.getCmp('userManage_userGrid');
        	searchParamObj.page = 1;
        	userGrid.store.reload({
        		params:searchParamObj,
        	});
    	});
    }

});