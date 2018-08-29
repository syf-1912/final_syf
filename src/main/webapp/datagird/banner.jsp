<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    $(function () {
        $('#dg').datagrid({
            url: '${pageContext.request.contextPath}/banner/showAll',
            columns: [[
                {field: 'id', title: '编号', width: 100},
                {field: 'title', title: '名称', width: 100},
                {field: 'description', title: '描述', width: 100},
                {field: 'status', title: '状态', width: 100},
                {field: 'createDate', title: '创建日期', width: 100}
            ]],
            fit: true,
            fitColumns: true,
            pagination: true,
            pageList: [5, 10, 15, 20],
            pageSize: 5
        });
    });
</script>

<table id="dg"></table>

