<%--
  Created by IntelliJ IDEA.
  User: hoangtrungkien
  Date: 13/01/2023
  Time: 23:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<c:url var="buildingListURL" value="/admin/building-list"/>
<c:url var="buildingAPIURL" value="/api/building"/>
<c:url var="buildingSearchURL" value="/api/building"/>
<c:url var="assignBuildingURL" value="/api/building/assignment"/>
<c:url var="assignmentURL" value="/api/building/assignment"/>
<c:url var="customerEditURL" value="/api/building"/>
<c:url var="editURL" value="/api/building-edit"/>
<html>
<head>
    <title>Danh sách toà nhà</title>
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
                <li class="active">Danh sách toà nhà</li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="widget-box">
                        <div class="widget-header">
                            <h4 class="widget-title">Tim kiem</h4>

                            <div class="widget-toolbar">
                                <a href="#" data-action="collapse">
                                    <i class="ace-icon fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main">
                                <form:form commandName="modelSearch" action="${buildingListURL}" id="listForm"
                                           method="GET">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <div class="col-sm-6">
                                                <div>
                                                    <label for="name">Tên toà nhà</label>
                                                    <form:input path="name" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="col-sm-6">
                                                <div>
                                                    <label for="floorArea">Diện tích sàn</label>
                                                    <form:input path="floorArea" cssClass="form-control"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-4">
                                                <div>
                                                    <label for="district">Quận hiện có</label>
                                                    <form:select path="district" cssStyle="margin-top: 6px" cssClass="form-control">
                                                        <label>Quận hiện có</label>
                                                        <form:option value="" label="--- Chọn Quận"/>
                                                        <form:options items="${districtmapsbyconstant}"/>
                                                    </form:select>
                                                </div>
                                            </div>
                                            <div class="col-sm-4">
                                                <label>Phường</label>
                                                <form:input path="ward" cssClass="form-control"/>
                                            </div>
                                            <div class="col-sm-4">
                                                <label>Đường</label>
                                                <form:input path="street" cssClass="form-control"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-4">
                                                <label for="numberOfBasement">Số tầng hầm</label>
                                                <form:input path="numberOfBasement" cssClass="form-control"/>
                                            </div>
                                            <div class="col-sm-4">
                                                <label for="direction">Hướng</label>
                                                <form:input path="direction" cssClass="form-control"/>
                                            </div>
                                            <div class="col-sm-4">
                                                <label for="level">Hạng</label>
                                                <form:input path="level" cssClass="form-control"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-3">
                                                <div>
                                                    <label for="rentAreaFrom">Diện tích từ (m2)</label>
                                                    <form:input path="rentAreaFrom" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="col-sm-3">
                                                <div>
                                                    <label for="rentAreaTo">Diện tích đến (m2)</label>
                                                    <form:input path="rentAreaTo" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="col-sm-3">
                                                <div>
                                                    <label for="rentPriceFrom">Giá thuê từ (triệu)</label>
                                                    <form:input path="rentPriceFrom" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="col-sm-3">
                                                <div>
                                                    <label for="rentPriceTo">Giá thuê đến (triệu)</label>
                                                    <form:input path="rentPriceTo" cssClass="form-control"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-4">
                                                <label for="managerName">Tên quản lý</label>
                                                <form:input path="managerName" cssClass="form-control"/>
                                            </div>
                                            <div class="col-sm-4">
                                                <label for="managerPhone">Điện thoại quản lý</label>
                                                <form:input path="managerPhone" cssClass="form-control"/>
                                            </div>
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
                                        <div class="form-group">
                                            <div class="col-sm-12">
                                                <label for="buildingTypes">Loại toà nhà</label>
                                                <form:checkboxes path="buildingTypes" items="${typemapsbyconstant}" cssStyle="margin: 5px"/>
                                            </div>
                                        </div>
                                        <button type="button" class="btn btn-sm btn-success" id="btnSearch">Tìm kiếm
                                        </button>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </div><!-- /.row -->
<%--            <div class="row">--%>
<%--                <div class="col-xs-12">--%>
<%--                    <div class="pull-right">--%>
<%--                        <security:authorize access="hasRole('MANAGER')">--%>
<%--                            <a href='<c:url value='/admin/building-edit-${item.id}'/>' class="btn btn-xs btn-info"--%>
<%--                               title="Thêm toà nhà">--%>
<%--                                <i class="fa fa-plus-circle" aria-hidden="true"></i>--%>
<%--                            </a>--%>
<%--                        </security:authorize>--%>
<%--                        <security:authorize access="hasRole('MANAGER')">--%>
<%--                            <button id="btnDelete" type="button" disabled--%>
<%--                                    class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"--%>
<%--                                    data-toggle="tooltip"--%>
<%--                                    title="Xoá toà nhà" onclick="warningBeforeDelete()">--%>
<%--															<span>--%>
<%--																<i class="fa fa-trash-o bigger-110 pink"></i>--%>
<%--															</span>--%>
<%--                            </button>--%>
<%--                        </security:authorize>--%>

<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
            <div class="table-btn-controls">
                <div class="pull-right tableTools-container">
                    <div class="dt-buttons btn-overlap btn-group">
                        <security:authorize access="hasRole('MANAGER')">
                            <a flag="info"
                               class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
                               data-toggle="tooltip"
                               title="Thêm toà nhà"
                               href='<c:url value="/admin/building-edit-${item.id}"/>'>
															<span>
																<i class="fa fa-plus-circle bigger-110 purple"></i>
															</span>
                            </a>
                            <button id="btnDelete" type="button" disabled
                                    class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
                                    data-toggle="tooltip"
                                    title="Xóa toà nhà" onclick="warningBeforeDelete()">
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
                    <display:table name="buildingSearch.listResult" cellspacing="0" cellpadding="0"
                                   requestURI="${buildingListURL}" partialList="true" sort="external"
                                   size="${buildingSearch.totalItems}" defaultsort="2" defaultorder="ascending"
                                   id="tableList" pagesize="${buildingSearch.maxPageItems}"
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
                        <display:column headerClass="text-left" property="name" title="Tên"/>
                        <display:column headerClass="text-left" property="numberOfBasement" title="Số tầng hầm"/>
                        <display:column headerClass="text-left" property="address" title="Địa chỉ"/>
                        <display:column headerClass="text-left" property="floorArea" title="Diện tích sàn (m2)"/>
                        <display:column headerClass="col-actions" title="Thao tác">
                            <button class="btn btn-xs btn-info" data-toggle="tooltip"
                                    title="Giao nhân viên" onclick="assignmentBuilding(${tableList.id})">
                                <i class="fa fa-bars" aria-hidden="true"></i>
                            </button>
                            <a href='<c:url value='/admin/building-edit-${tableList.id}'/>' class="btn btn-xs btn-info"
                               title="Chỉnh sửa toà nhà">
                                <i class="ace-icon fa fa-pencil bigger-120"></i>
                            </a>
                        </display:column>
                    </display:table>
                </div>
            </div>


<%--            //Server-side rendering--%>
<%--            <div class="row">--%>
<%--                <div class="col-xs-12">--%>
<%--                    <table cellspacing="0" cellpadding="0"--%>
<%--                           id="tableList"--%>
<%--                           class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"--%>
<%--                           style="margin: 3em 0 1.5em;">--%>
<%--                        <thead>--%>
<%--                        <tr>--%>
<%--                            <th class="center select-cell">--%>
<%--                                <fieldset class='form-group'>--%>
<%--                                    <input type='checkbox' id='checkAll' class='check-box-element'>--%>
<%--                                </fieldset>--%>
<%--                            </th>--%>
<%--                            <th class="text-left">Tên</th>--%>
<%--                            <th class="text-left">Số tầng hầm</th>--%>
<%--                            <th class="text-left">Địa chỉ</th>--%>
<%--                            <th class="text-left">Diện tích sàn</th>--%>
<%--                            <th class="col-actions">Thao tác</th>--%>
<%--                        </tr>--%>
<%--                        </thead>--%>
<%--                        <tbody>--%>

<%--                        </tbody>--%>
<%--                    </table>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            //******--%>

        </div><!-- /.page-content -->
    </div>
</div><!-- /.main-content -->

<!--Modal-->
<div id="assignmentBuildingModal" class="modal fade" role="dialog">
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
                <input type="hidden" id="buildingId" name="buildingId" value="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="btnAssignBuilding">Giao toà nhà</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>

    </div>
</div>

<script>


    // Server-side rendering for search functionality **********************************************
    <%--function searchBuilding() {--%>
    <%--    var data = {};--%>
    <%--    var buildingTypes = [];--%>
    <%--    var formData = $('#listForm').serializeArray();--%>
    <%--    $.each(formData, function (index, v) {--%>
    <%--        data[v.name] = v.value;--%>
    <%--    });--%>

    <%--    $.ajax({--%>
    <%--        type: 'GET',--%>
    <%--        url: "${buildingAPIURL}",--%>
    <%--        data: data,--%>
    <%--        dataType: "json",--%>
    <%--        contentType: "application/json",--%>
    <%--        success: function (response) {--%>
    <%--            $("#tableList tbody").empty();--%>
    <%--            var newRow = "";  // move newRow declaration here--%>
    <%--            $.each(response, function(index, building) {--%>
    <%--                newRow += "<tr>" +--%>
    <%--                    "<td class='center select-cell'>" +--%>
    <%--                    "<fieldset>" +--%>
    <%--                    "<input type='checkbox' name='checkList' value='" + building.id +--%>
    <%--                    "' id='checkbox_" + building.id + "' class='check-box-element'/>" +--%>
    <%--                    "</fieldset>" +--%>
    <%--                    "</td>" +--%>
    <%--                    "<td class='text-left'>" + building.name + "</td>" +--%>
    <%--                    "<td class='text-left'>" + building.numberOfBasement + "</td>" +--%>
    <%--                    "<td class='text-left'>" + building.address + "</td>" +--%>
    <%--                    "<td class='text-left'>" + building.floorArea + "</td>" +--%>
    <%--                    "<td class='col-actions'>" +--%>
    <%--                    "<button class='btn btn-xs btn-info' data-toggle='tooltip' " +--%>
    <%--                    "title='Giao toa nha' onclick='assignmentBuilding(" + building.id + ")'>" +--%>
    <%--                    "<i class='fa fa-bars' aria-hidden='true'></i>" +--%>
    <%--                    "</button>" +--%>
    <%--                    "<a href='/admin/building-edit-" + building.id + "' class='btn btn-xs btn-info' " +--%>
    <%--                    "title='Chinh sua toa nha'>" +--%>
    <%--                    "<i class='ace-icon fa fa-pencil bigger-120'></i>" +--%>
    <%--                    "</a>" +--%>
    <%--                    "</td>" +--%>
    <%--                    "</tr>";--%>
    <%--            });--%>
    <%--            $("#tableList tbody").append(newRow);  // append all rows at once--%>
    <%--        },--%>
    <%--        error: function (response) {--%>
    <%--            console.log('failed');--%>
    <%--            console.log(response);--%>
    <%--        }--%>
    <%--    });--%>
    <%--}--%>

    <%--$(document).ready(function() {--%>
    <%--    searchBuilding();--%>
    <%--});--%>

    <%--$('#btnSearch').click(function (e) {--%>
    <%--    e.preventDefault();--%>
    <%--    searchBuilding();--%>
    <%--});--%>
    // **********************************************




    function assignmentBuilding(buildingId) {
        openModalAssignmentBuilding();
        loadStaff(buildingId);
        $('#buildingId').val(buildingId);
        console.log($('#buildingId').val());
    }

    function loadStaff(buildingId) {
        $.ajax({
            type: "GET",
            url: "${buildingAPIURL}/" + buildingId + "/staffs",
            //data: JSON.stringify(data),
            dataType: "json",
            //contentType: "application/json",
            success: function (response) {
                //console.log('success');
                var row = '';
                $.each(response.data, function (index, item) {
                    row += '<tr>';
                    row += '<td class="text-center"><input type="checkbox" value=' + item.staffId + ' id="checkbox_' + item.staffId + '" class="check-box-element" ' + item.checked + '/></td>';
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

    function openModalAssignmentBuilding() {
        $('#assignmentBuildingModal').modal();
    }

    $('#btnAssignBuilding').click(function (e) {
        e.preventDefault();
        var data = {};
        let buildingId = $('#buildingId').val();
        var staffIds = $('#staffList').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        data['staffIds'] = staffIds;
        data['buildingId'] = buildingId;
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
                window.location.href = "<c:url value='/admin/building-list?message=success'/>";
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
            const buildingIds = $('#tableList').find('tbody input[type=checkbox][name="checkList"]:checked')
                .map(function () {
                    return $(this).val();
                }).get();
            data['buildingIds'] = buildingIds;
            deleteBuilding(data);
        });
    }

    function deleteBuilding(data) {
        $.ajax({
            type: "DELETE",
            url: "${buildingAPIURL}",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                window.location.href = "<c:url value='/admin/building-list?message=success'/>";
            },
            error: function (response) {
                console.log('failed');
                console.log(response);
            }
        });
    }

    function reload() {
        window.location.href = "${buildingListURL}";
    }

    $('#btnSearch').click(function (e) {
        e.preventDefault();
        $('#listForm').submit();
    });
</script>

</body>
</html>
