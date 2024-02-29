<%--
  Created by IntelliJ IDEA.
  User: hoangtrungkien
  Date: 26/02/2023
  Time: 22:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<c:url var="customerListURL" value="/admin/customer-list"/>
<c:url var="customerAPIURL" value="/api/customer"/>
<c:url var="assignBuildingURL" value="/api/customer/assignment"/>
<c:url var="editURL" value="/admin/customer-edit"/>
<c:url var="assignmentURL" value="/api/customer/assignment"/>
<html>
<head>
    <title>Danh sách khách hàng</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check('breadcrumbs', 'fixed')
                } catch (e) {
                }
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Home</a>
                </li>
                <li class="active">Danh sách khách hàng</li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="widget-box">
                        <div class="widget-header">
                            <h4 class="widget-title">Tìm kiếm</h4>

                            <div class="widget-toolbar">
                                <a href="#" data-action="collapse">
                                    <i class="ace-icon fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main">
                                <form:form commandName="modelSearch" action="${customerListURL}" id="listForm"
                                           method="GET">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <div class="col-sm-4">
                                                <label for="fullName">Tên đầy đủ</label>
                                                <form:input path="fullName" cssClass="form-control" />
                                            </div>
                                            <div class="col-sm-4">
                                                <label for="email">Email</label>
                                                <form:input path="email" cssClass="form-control" />
                                            </div>
                                            <div class="col-sm-4">
                                                <label for="phone">Phone</label>
                                                <form:input path="phone" cssClass="form-control" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-4">
                                                <security:authorize access="hasRole('MANAGER')">
                                                    <label for="staffId">Chọn nhân viên phụ trách</label>
                                                    <form:select path="staffId" cssStyle="margin-top: 6px" cssClass="form-control">
                                                        <label for="staffId">Chọn nhân viên phụ trách</label>
                                                        <form:option value="" label="--- Chọn nhân viên"/>
                                                        <form:options items="${staffmaps}"/>
                                                    </form:select>
                                                </security:authorize>
                                            </div>
                                        </div>
                                        <button type="button" class="btn btn-sm btn-success" id="btnSearch">Tìm kiếm</button>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="table-btn-controls">
                <div class="pull-right tableTools-container">
                    <div class="dt-buttons btn-overlap btn-group">
                        <security:authorize access="hasRole('MANAGER')">
                            <a flag="info"
                               class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
                               data-toggle="tooltip"
                               title="Thêm khách hàng"
                               href='<c:url value="/admin/customer-edit"/>'>
															<span>
																<i class="fa fa-plus-circle bigger-110 purple"></i>
															</span>
                            </a>
                            <button id="btnDelete" type="button" disabled
                                    class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
                                    data-toggle="tooltip"
                                    title="Xóa khách hàng" onclick="warningBeforeDelete()">
															<span>
																<i class="fa fa-trash-o bigger-110 pink"></i>
															</span>
                            </button>
                        </security:authorize>
                    </div>
                </div>
            </div>
            <br/>
            <div class="row">
                <div class="col-xs-12">
                    <display:table name="customerSearch.listResult" cellspacing="0" cellpadding="0"
                                   requestURI="${customerListURL}" partialList="true" sort="external"
                                   size="${customerSearch.totalItems}" defaultsort="2" defaultorder="ascending"
                                   id="tableList" pagesize="${customerSearch.maxPageItems}"
                                   export="false"
                                   class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                   style="margin: 3em 0 1.5em;">
                        <display:column title="<fieldset class='form-group'>
												        <input type='checkbox' id='checkAll' class='check-box-element'>
												        </fieldset>" class="center select-cell"
                                        headerClass="center select-cell">
                            <fieldset>
                                <input type="checkbox" name="checkList" value="${tableList.id}"
                                       id="checkbox_${tableList.id}" class="check-box-element"/>
                            </fieldset>
                        </display:column>
                        <display:column headerClass="text-left" property="fullName" title="Tên"/>
                        <display:column headerClass="text-left" property="staffName" title="Nhân viên quản lý"/>
                        <display:column headerClass="text-left" property="phone" title="Di động"/>
                        <display:column headerClass="text-left" property="email" title="Email"/>
                        <display:column headerClass="text-left" property="demand" title="Nhu cầu"/>
                        <display:column headerClass="text-left" property="createdBy" title="Người nhập"/>
                        <display:column headerClass="text-left" property="createdDate" title="Ngày nhập"/>
                        <display:column headerClass="col-actions" title="Thao tác">
                            <button class="btn btn-xs btn-info" data-toggle="tooltip"
                                    title="Giao nhân viên" onclick="assignmentCustomer(${tableList.id})">
                                <i class="fa fa-bars" aria-hidden="true"></i>
                            </button>
                            <a href='<c:url value='/admin/customer-edit-${tableList.id}'/>' class="btn btn-xs btn-info" title="Chỉnh sửa khách hàng">
                                <i class="ace-icon fa fa-pencil bigger-120"></i>
                            </a>
                        </display:column>
                    </display:table>
                </div>
            </div>
        </div><!-- /.page-content -->
    </div>
</div><!-- /.main-content -->

<!--Modal-->
<div id="assignmentCustomerModal" class="modal fade" role="dialog">
    <div class="modal-dialog">


        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Danh sách nhân viên</h4>
            </div>
            <div class="modal-body">
                <table class="table table-bordered" id="staffList">
                    <thead>
                    <tr>
                        <th>Chọn nhân viên</th>
                        <th>Tên nhân viên</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
                <input type="hidden" id="customerId" name="customerId" value="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="btnAssignCustomer">Giao khách hàng</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>

    </div>
</div>

<script  type="text/javascript">

    function assignmentCustomer(customerId) {
        openModalAssignmentCustomer();
        loadStaff(customerId);
        $('#customerId').val(customerId);
        console.log($('#customerId').val());
    }

    function loadStaff(customerId){
        $.ajax({
            type: "GET",
            url: "${customerAPIURL}/"+customerId+"/staffs",
            dataType: "json",
            success: function (response) {
                //console.log('success');
                var row = '';
                $.each(response.data, function(index,item){
                    row += '<tr>';
                    row += '<td class="text-center"><input type="checkbox" value=' +item.staffId + ' id="checkbox_' + item.staffId+ '" class="check-box-element" ' + item.checked + '/></td>';
                    row += '<td class="text-center">' + item.fullName + '</td>';
                    row += '</tr>';
                });
                $('#staffList tbody').html(row);

            },
            error: function (response) {
                console.log('failed');
                console.log(response);
            }
        });
    }

    function openModalAssignmentCustomer() {
        $('#assignmentCustomerModal').modal();
    }

    $('#btnAssignCustomer').click(function (e) {
        e.preventDefault();
        var data = {};
        let customerId = $('#customerId').val();
        var staffIds = $('#staffList').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        data['staffIds'] = staffIds;
        data['customerId'] = customerId;
        assignStaff(data);
    });

    function assignStaff(data) {
        $.ajax({
            type: "POST",
            url: "${assignmentURL}",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                console.log('success');
                window.location.href = "<c:url value='/admin/customer-list?message=success'/>";
            },
            error: function (response) {
                console.log('failed');
                console.log(response);
            }
        });
    }

    function warningBeforeDelete() {
        showAlertBeforeDelete(function () {
            event.preventDefault();
            var data = {};
            const customerIds = $('#tableList').find('tbody input[type=checkbox][name="checkList"]:checked')
                .map(function () {
                    return $(this).val();
                }).get();
            data['customerIds'] = customerIds;
            deleteCustomer(data);
        });
    }

    function deleteCustomer(data) {
        $.ajax({
            type: "DELETE",
            url: "${customerAPIURL}",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                window.location.href = "<c:url value='/admin/customer-list?message=success'/>";
            },
            error: function (response) {
                window.location.href = "<c:url value='/admin/customer-list?message=error'/>";
                console.log('failed');
                console.log(response);
            }
        });
    }

    function reload(){
        window.location.href = "${buildingListURL}";
    }

    $('#btnSearch').click(function (e) {
        e.preventDefault();
        $('#listForm').submit();
    });
</script>

</body>
</html>
