<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户管理页面</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>

    <script type="text/javascript">


        function banUser(){
            var selectedRows=$("#dg").datagrid("getSelections");
            if(selectedRows.length==0){
                $.messager.alert("系统提示","请选择要封禁的用户！");
                return;
            }
            var strIds=[];
            for(var i=0;i<selectedRows.length;i++){
                strIds.push(selectedRows[i].id);
            }
            var ids=strIds.join(",");
            $.messager.confirm("系统提示","您确定要封禁这<font color=red>"+selectedRows.length+"</font>些用户吗？",function(r){
                if(r){
                    $.post("${pageContext.request.contextPath}/admin/blogger/ban",{ids:ids},function(result){
                        if(result.success){
                            $.messager.alert("系统提示","用户成功封禁！");
                            $("#dg").datagrid("reload");
                        }else{
                            $.messager.alert("系统提示","用户封禁失败！");
                        }
                    },"json");
                }
            });
        }

        function activiteUser(){
            var selectedRows=$("#dg").datagrid("getSelections");
            if(selectedRows.length==0){
                $.messager.alert("系统提示","请选择要解禁的用户！");
                return;
            }
            var strIds=[];
            for(var i=0;i<selectedRows.length;i++){
                strIds.push(selectedRows[i].id);
            }
            var ids=strIds.join(",");
            $.messager.confirm("系统提示","您确定要解禁这<font color=red>"+selectedRows.length+"</font>些用户吗？",function(r){
                if(r){
                    $.post("${pageContext.request.contextPath}/admin/blogger/lift",{ids:ids},function(result){
                        if(result.success){
                            $.messager.alert("系统提示","用户成功解禁！");
                            $("#dg").datagrid("reload");
                        }else{
                            $.messager.alert("系统提示","用户解禁失败！");
                        }
                    },"json");
                }
            });
        }

        function formatState(val,row){
            if(val==1){
                return "未封禁";
            }else if(val==2){
                return "已封禁";
            }
        }
    </script>
</head>
<body style="margin: 1px">
<table id="dg" title="用户管理" class="easyui-datagrid"
       fitColumns="true" pagination="true" rownumbers="true"
       url="${pageContext.request.contextPath}/admin/blogger/list" fit="true" toolbar="#tb">
    <thead>
    <tr>
        <th field="cb" checkbox="true" align="center"></th>
        <th field="id" width="20" align="center">编号</th>
        <th field="name" width="200" align="center">用户名</th>
        <th field="nickName" width="200" align="center">用户昵称</th>
        <th field="phone" width="200" align="center">用户电话</th>
        <th field="email" width="200" align="center">用户邮箱</th>
        <th field="registerDate" width="50" align="center">注册日期</th>
        <th field="state" width="50" align="center" formatter="formatState">封禁状态</th>
    </tr>
    </thead>
</table>
<div id="tb">
    <div>
        <a href="javascript:banUser()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">封禁</a>
        <a href="javascript:activiteUser()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">解封</a>
    </div>
</div>


</body>
</html>