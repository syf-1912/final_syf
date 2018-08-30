<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    pageContext.setAttribute("path", request.getContextPath());
%>
<script type="text/javascript">
    $(function () {

        var toolbar = [
            {
                iconCls: 'icon-add',
                text: '添加',
                handler: function () {
                    $('#al').dialog({
                        title: '添加商品'
                    });
                    //初始化表单
                    $('#ff').form({
                        url: '${path}/banner/add',
                        onSubmit: function (param) {
                            var b = $('#ff').form('validate');
                            if (b) return true;
                            else return false;
                        },
                        success: function (data) {
                            //关闭对话框
                            $('#al').dialog('close');
                            //刷新页面
                            $('#dg').edatagrid('reload');
                        }
                    });
                }
            }, '-',
            {
                iconCls: 'icon-remove',
                text: '删除',
                handler: function () {
                    //获取选中行数据
                    var row = $("#dg").edatagrid('getSelected');
                    if (row != null) {
                        $.messager.confirm('确认', '您确认想要删除吗？', function (r) {
                            if (r) {
                                $.ajax({
                                    url: '${path}/banner/delete',
                                    type: 'post',
                                    data: 'id=' + row.id,
                                    success: function () {
                                        $('#dg').edatagrid('reload');
                                    }
                                });
                            }
                        });
                    } else {
                        $.messager.alert('确认', '请选中一行数据');
                    }
                }
            }, '-',
            {
                iconCls: 'icon-edit',
                text: '修改',
                handler: function () {
                    //使选中的行进入可编辑模式
                    var row = $("#dg").edatagrid('getSelected');
                    if (row != null) {
                        //获取当前行的下标
                        var index = $('#dg').edatagrid('getRowIndex', row);
                        //编辑当前行
                        $('#dg').edatagrid('editRow', index);
                    } else {
                        $.messager.alert('确认', '请选中一行数据');
                    }
                }
            }, '-',
            {
                iconCls: 'icon-save',
                text: '保存',
                handler: function () {
                    $('#dg').edatagrid('saveRow');
                    $('#dg').edatagrid('reload');
                }
            }
        ];
        $('#dg').edatagrid({
            url: '${pageContext.request.contextPath}/banner/showAll',
            updateUrl: "${pageContext.request.contextPath}/banner/update",
            columns: [[
                {field: 'ck', checkbox: true, width: 100},
                {field: 'id', title: '编号', width: 100},
                {field: 'title', title: '名称', width: 100},
                {field: 'description', title: '描述', width: 100},
                {
                    field: 'status', title: '状态', width: 100, editor: {
                        type: 'text',
                        options: {
                            required: true
                        }
                    }
                },
                {field: 'createDate', title: '创建日期', width: 100}
            ]],
            fit: true,
            fitColumns: true,
            pagination: true,
            pageList: [5, 10, 15, 20],
            pageSize: 5,
            toolbar: toolbar,
            ctrlSelect: true,
            view: detailview,
            detailFormatter: function (rowIndex, rowData) {
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}' + rowData.imgPath + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>CreateDate: ' + rowData.createDate + '</p>' +
                    '<p>Status: ' + rowData.status + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }

        });

        //提交表单
        $('#subAdd').click(function () {
            $('#ff').submit();
        });
        //重置表单
        $('#resetAdd').click(function () {
            $('#ff').form('clear');
        });
    });
</script>

<table id="dg"></table>
<div style="padding:30px " id="al">
    <form id="ff" method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <td>名称</td>
                <td>
                    <input type="text" name="title" class="easyui-textbox" data-options="required:true">
                </td>
            </tr>
            <tr>
                <td>描述</td>
                <td>
                    <input type="text" name="description" class="easyui-textbox" data-options="required:true">
                </td>
            </tr>
            <tr>
                <td>状态</td>
                <td>
                    <select class="easyui-combobox" name="status" style="width:200px;">
                        <option value="Y">展示</option>
                        <option value="N">不展示</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>图片</td>
                <td>
                    <input type="text" name="img" class="easyui-filebox" data-options="required:true">
                </td>
            </tr>
            <tr align="center">
                <td colspan="2">
                    <a href="javascript:void(0)" class="easyui-linkbutton"
                       data-options="iconCls:'icon-ok',text:'提交'" id="subAdd"></a>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="javascript:void(0)" class="easyui-linkbutton"
                       data-options="iconCls:'icon-undo',text:'重置'" id="resetAdd"></a>
                </td>
            </tr>
        </table>
    </form>
</div>


