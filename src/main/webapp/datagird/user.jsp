<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    pageContext.setAttribute("path", request.getContextPath());
%>
<script type="text/javascript">
    $(function () {
        $("#btn").click(function (data) {
            var titles = $("#customer_tree").combotree("getText");
            var params = $("#customer_tree").combotree("getValues");
            var page = $('#user_tt').datagrid('options');
            var a = "";
            $.each(params, function (index, param) {
                if (params.length - 1 == index) {
                    a += param;
                } else {
                    a += param + ",";
                }
            });
            $("#customer_form").form("submit", {
                url: "${path}/user/customerExport",
                queryParams: {
                    titles: titles,
                    params: a,
                    page: page.pageNumber,
                    rows: page.pageSize
                }
            })
        });
        $('#btn_1').click(function () {
            $('#import_form').form('submit', {
                url: '${path}/user/import',
                success: function (data) {
                    //关闭对话框
                    $('#import_dd').dialog('close');
                    //刷新页面
                    $('#user_tt').datagrid('reload');
                }
            })
        });
        var toolbar = [
            {
                iconCls: 'icon-add',
                text: '全部导出',
                handler: function () {
                    var page = $('#user_tt').datagrid('options');
                    $.messager.confirm('确认', '您确定要导出吗？', function (r) {
                        if (r) {
                            location.href = "${path}/user/exportAll?page=" + page.pageNumber + "&rows=" + page.pageSize;
                        }
                    });
                }
            }, '-',
            {
                iconCls: 'icon-remove',
                text: '自定义导出',
                handler: function () {
                    $("#customer_dd").dialog("open");
                }
            }, '-',
            {
                iconCls: 'icon-edit',
                text: '导入模板下载',
                handler: function () {
                    location.href = "${path}/user/downLoad";
                }
            }, '-',
            {
                iconCls: 'icon-save',
                text: '导入',
                handler: function () {
                    $("#import_dd").dialog("open");
                }
            }
        ];
        $('#user_tt').datagrid({
            url: '${path}/user/showAll',
            columns: [[
                {field: 'ck', checkbox: true, width: 100},
                {field: 'id', title: '编号', width: 100},
                {field: 'name', title: '姓名', width: 100},
                {field: 'dharmaName', title: '法名', width: 100},
                {field: 'sex', title: '性别', width: 100},
                {field: 'province', title: '省份', width: 100},
                {field: 'city', title: '城市', width: 100},
                {field: 'phone', title: '手机', width: 100},
                {field: 'sign', title: '签名', width: 100},
                {field: 'status', title: '状态', width: 100},
                {field: 'registDate', title: '注册时间', width: 100}
            ]],
            fit: true,
            fitColumns: true,
            pagination: true,
            pageList: [5, 10, 15, 20],
            pageSize: 10,
            toolbar: toolbar,
            ctrlSelect: true,
            view: detailview,
            detailFormatter: function (rowIndex, rowData) {
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${path}' + rowData.photoImg + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>RegistDate: ' + rowData.registDate + '</p>' +
                    '<p>Status: ' + rowData.status + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }

        });

    });
</script>
<table id="user_tt"></table>
<div id="customer_dd" class="easyui-dialog" title="选择导出项" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    <select id="customer_tree" class="easyui-combotree" style="width:200px;"
            data-options="multiple:true,onlyLeafCheck:true,required:true,
            data:[{
    'id':'custome',
    'checked':false,
    'text': '请选择',
    'children': [
      {
        'id':'id',
        'text': '编号',
        'checked': true
      },
      { 'id':'name',
        'text': '姓名',
        'checked': true
      },
      { 'id':'dharmaName',
        'text': '法名',
        'checked': true
      },
      { 'id':'sex',
        'text': '性别',
        'checked': true
      },
      { 'id':'province',
        'text': '省份',
        'checked': true
      },
      { 'id':'city',
        'text': '城市',
        'checked': true
      },
      { 'id':'phone',
        'text': '手机',
        'checked': true
      },
      { 'id':'sign',
        'text': '签名',
        'checked': true
      },
      { 'id':'status',
        'text': '状态',
        'checked': true
      },
      { 'id':'registDate',
        'text': '注册时间',
        'checked': true
      }
    ]
  }
]"></select>
    <form action="" method="post" id="customer_form">
        <a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">提交</a>
    </form>

</div>
<div id="import_dd" class="easyui-dialog" title="导入" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    <form action="" method="post" id="import_form" enctype="multipart/form-data">
        <input name="file" class="easyui-filebox" style="width:300px">
        <a id="btn_1" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">提交</a>
    </form>

</div>

