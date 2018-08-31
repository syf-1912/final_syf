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
                text: '专辑详情',
                handler: function () {

                    var row = $('#tt1').treegrid('getSelected');
                    if (row != null) {
                        if (row.status != null) {
                            $('#dd').dialog('open');
                            $('#tb').html("");
                            var tr1 = $("<tr><td>专辑名称：" + row.title + "</td></tr>");
                            var tr2 = $("<tr><td>章节数量：" + row.count + "</td></tr>");
                            var tr3 = $("<tr><td>评分：" + row.score + "</td></tr>");
                            var tr4 = $("<tr><td>状态：" + row.status + "</td></tr>");
                            var tr5 = $("<tr><td>作者：" + row.author + "</td></tr>");
                            var tr6 = $("<tr><td>播音人：" + row.broadCast + "</td></tr>");
                            var tr7 = $("<tr><td>发布日期：" + row.publicDate + "</td></tr>");
                            var tr8 = $("<tr><td>内容简介：" + row.brife + "</td></tr>");
                            var tr9 = $("<tr><td>专辑封面：<img src='${path}/" + row.corverImg + "'></td></tr>");
                            $('#tb').append(tr1, tr2, tr3, tr4, tr5, tr6, tr7, tr8);

                        } else {
                            $.messager.alert('确认', '请选中一张专辑');
                        }
                    } else {
                        $.messager.alert('确认', '您尚未选中任何数据');
                    }
                }
            }, '-',
            {
                iconCls: 'icon-remove',
                text: '添加专辑',
                handler: function () {
                    $('#dd1').dialog('open');
                    //初始化表单
                    $('#ff1').form({
                        url: '${path}/album/add',
                        onSubmit: function (param) {
                            var b = $('#ff1').form('validate');
                            if (b) return true;
                            else return false;
                        },
                        success: function (data) {
                            //关闭对话框
                            $('#dd1').dialog('close');
                            //刷新页面
                            $('#tt1').treegrid('reload');
                        }
                    });
                }
            }, '-',
            {
                iconCls: 'icon-edit',
                text: '添加章节',
                handler: function () {
                    var roots = $('#tt1').treegrid('getRoots');
                    for (var i = 0; i < roots.length; i++) {
                        console.log(roots[i]);
                    }
                    //初始化专辑下拉框
                    $('#albumId').combobox({
                        valueField: 'id',
                        textField: 'title',
                        data: roots
                    });
                    $('#dd2').dialog('open');
                    //初始化表单
                    $('#ff2').form({
                        url: '${path}/chapter/add',
                        onSubmit: function (param) {
                            var b = $('#ff2').form('validate');
                            if (b) return true;
                            else return false;
                        },
                        success: function (data) {
                            //关闭对话框
                            $('#dd2').dialog('close');
                            //刷新页面
                            $('#tt1').treegrid('reload');
                        }
                    });
                }
            }, '-',
            {
                iconCls: 'icon-save',
                text: '下载音频',
                handler: function () {
                    var row = $('#tt1').treegrid('getSelected');
                    if (row != null) {
                        if (row.status == null) {
                            location.href = "${path}/chapter/down?name=" + row.title + "&url=" + row.audioPath;
                        } else {
                            $.messager.alert('确认', '请选中音频');
                        }
                    } else {
                        $.messager.alert('确认', '您尚未选中任何数据');
                    }
                }
            }
        ];

        $('#tt1').treegrid({
            url: '${path}/album/showAll',
            idField: 'id',
            treeField: 'title',
            columns: [[
                {field: 'title', title: '名称', width: 60},
                {field: 'duration', title: '章节时长', width: 80},
                {field: 'size', title: '章节大小', width: 80},
                {field: 'audioPath', title: '下载路径', width: 80}
            ]],
            fit: true,
            fitColumns: true,
            pagination: true,
            toolbar: toolbar,
            onDblClickRow: function (row) {
                $("#audio_dd").dialog("open");
                $("#audio").prop("src", "${path}" + row.audioPath);
            }
        });
    });
    //提交表单
    $('#subAdd').click(function () {
        $('#ff1').submit();
    });
    //重置表单
    $('#resetAdd').click(function () {
        $('#ff1').form('clear');
    });
    //提交表单
    $('#subAdd1').click(function () {
        $('#ff2').submit();
    });
    //重置表单
    $('#resetAdd1').click(function () {
        $('#ff2').form('clear');
    });
</script>

<table id="tt1"></table>
<div id="dd" class="easyui-dialog" title="专辑详情" style="width:800px;height:400px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    <table>
        <tbody id="tb"></tbody>
    </table>
</div>

<div style="padding:30px " id="dd1" class="easyui-dialog" title="添加专辑"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    <form id="ff1" method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <td>专辑名称</td>
                <td>
                    <input type="text" name="title" class="easyui-textbox" data-options="required:true">
                </td>
            </tr>
            <tr>
                <td>作者</td>
                <td>
                    <input type="text" name="author" class="easyui-textbox" data-options="required:true">
                </td>
            </tr>
            <tr>
                <td>播音人</td>
                <td>
                    <input type="text" name="broadCast" class="easyui-textbox" data-options="required:true">
                </td>
            </tr>
            <tr>
                <td>评分</td>
                <td>
                    <input type="text" name="score" class="easyui-textbox" data-options="required:true">
                </td>
            </tr>
            <tr>
                <td>内容简介</td>
                <td>
                    <input type="text" name="brife" class="easyui-textbox" data-options="required:true">
                </td>
            </tr>
            <tr>
                <td>章节数量</td>
                <td>
                    <input type="text" name="count" class="easyui-textbox" data-options="required:true">
                </td>
            </tr>
            <tr>
                <td>状态</td>
                <td>
                    <select class="easyui-combobox" name="status" style="width:200px;">
                        <option value="Y">发布</option>
                        <option value="N">不发布</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>专辑封面</td>
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

<div style="padding:30px " id="dd2" class="easyui-dialog" title="添加章节"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    <form id="ff2" method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <td>章节名称</td>
                <td>
                    <input type="text" name="title" class="easyui-textbox" data-options="required:true">
                </td>
            </tr>
            <tr>
                <td>章节路径</td>
                <td>
                    <input type="text" name="audio" class="easyui-filebox" data-options="required:true">
                </td>
            </tr>
            <tr>
                <td>所属专辑</td>
                <td>
                    <input id="albumId" class="easyui-combobox" name="albumId">
                </td>
            </tr>
            <tr align="center">
                <td colspan="2">
                    <a href="javascript:void(0)" class="easyui-linkbutton"
                       data-options="iconCls:'icon-ok',text:'提交'" id="subAdd1"></a>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="javascript:void(0)" class="easyui-linkbutton"
                       data-options="iconCls:'icon-undo',text:'重置'" id="resetAdd1"></a>
                </td>
            </tr>
        </table>
    </form>
</div>

<div id="audio_dd" class="easyui-dialog" title="播放专辑" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    <div style="width:200px;height:100px;margin-top:50px;">
        <audio src="" id="audio" controls="controls">

        </audio>
    </div>
</div>