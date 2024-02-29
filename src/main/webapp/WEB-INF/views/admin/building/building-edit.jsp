<%--
  Created by IntelliJ IDEA.
  User: hoangtrungkien
  Date: 18/01/2023
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
<c:url var="buildingAPI" value="/api/building"/>
<c:url var="buildingEditURL" value="/admin/building-edit"/>
<c:url var="buildingListURL" value="/admin/building-list"/>
<html>
<head>
    <title>Chỉnh sửa toà nhà</title>
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
                <li class="active">Chỉnh sửa toà nhà</li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <form:form commandName="modelEdit" action="${buildingEditURL}" id="formEdit" method="POST">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="name">Tên toà nhà</label>
                            <div class="col-sm-9">
                                <form:input path="name" cssClass="form-control" cssStyle="margin: 5px"/>
                            </div>
                        </div>
                        <div class="space-4"></div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="name">Quận hiện có</label>
                            <div class="col-sm-9">
                                <form:select path="district" cssClass="form-control" cssStyle="margin: 5px">
                                    <form:option value="" label="--- Chọn Quận"/>
                                    <form:options items="${districtmapsbyconstant}"/>
                                </form:select>
                            </div>
                        </div>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="ward">Phường</label>
                            <div class="col-sm-9">
                                <form:input path="ward" cssClass="form-control" cssStyle="margin: 5px"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="street">Đường</label>
                            <div class="col-sm-9">
                                <form:input path="street" cssClass="form-control" cssStyle="margin: 5px"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="structure">Kết cấu</label>
                            <div class="col-sm-9">
                                <form:input path="structure" cssClass="form-control" cssStyle="margin: 5px"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="numberOfBasement">Số tầng hầm</label>
                            <div class="col-sm-9">
                                <form:input path="numberOfBasement" cssClass="form-control" cssStyle="margin: 5px"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="floorArea">Diện tích sàn (m2)</label>
                            <div class="col-sm-9">
                                <form:input path="floorArea" cssClass="form-control" cssStyle="margin: 5px"/>

                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="direction">Hướng</label>
                            <div class="col-sm-9">
                                <form:input path="direction" cssClass="form-control" cssStyle="margin: 5px"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="level">Hạng</label>
                            <div class="col-sm-9">
                                <form:input path="level" cssClass="form-control" cssStyle="margin: 5px"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="rentArea">Diện tích thuê (m2)</label>
                            <div class="col-sm-9">
                                <form:input path="rentArea" cssClass="form-control" cssStyle="margin: 5px"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="rentAreaDescription">Mô tả diện tích</label>
                            <div class="col-sm-9">
                                <form:input path="rentAreaDescription" cssClass="form-control" cssStyle="margin: 5px"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="rentPriceDescription">Mô tả giá</label>
                            <div class="col-sm-9">
                                <form:input path="rentPriceDescription" cssClass="form-control" cssStyle="margin: 5px"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="serviceFee">Phí dịch vụ (triệu)</label>
                            <div class="col-sm-9">
                                <form:input path="serviceFee" cssClass="form-control" cssStyle="margin: 5px"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="carCost">Phí ô tô (triệu)</label>
                            <div class="col-sm-9">
                                <form:input path="carCost" cssClass="form-control" cssStyle="margin: 5px"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="motorbikeFee">Phí mô tô (triệu)</label>
                            <div class="col-sm-9">
                                <form:input path="motorbikeFee" cssClass="form-control" cssStyle="margin: 5px"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="overtimeFee">Phí ngoài giờ (triệu)</label>
                            <div class="col-sm-9">
                                <form:input path="overtimeFee" cssClass="form-control" cssStyle="margin: 5px"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="electricBill">Tiền điện (triệu)</label>
                            <div class="col-sm-9">
                                <form:input path="electricBill" cssClass="form-control" cssStyle="margin: 5px"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="deposit">Đặt cọc (tháng)</label>
                            <div class="col-sm-9">
                                <form:input path="deposit" cssClass="form-control" cssStyle="margin: 5px"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="payment">Thanh toán (tháng)</label>
                            <div class="col-sm-9">
                                <form:input path="payment" cssClass="form-control" cssStyle="margin: 5px"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="rentTime">Thời gian thuê (tháng)</label>
                            <div class="col-sm-9">
                                <form:input path="rentTime" cssClass="form-control" cssStyle="margin: 5px"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="decorationTime">Thời gian trang trí</label>
                            <div class="col-sm-9">
                                <form:input path="decorationTime" cssClass="form-control" cssStyle="margin: 5px"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="rentPrice">Giá thuê (triệu)</label>
                            <div class="col-sm-9">
                                <form:input path="rentPrice" cssClass="form-control" cssStyle="margin: 5px"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="managerName">Tên quản lý</label>
                            <div class="col-sm-9">
                                <form:input path="managerName" cssClass="form-control" cssStyle="margin: 5px"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="managerPhone">Số điện thoại quản lý
                            </label>
                            <div class="col-sm-9">
                                <form:input path="managerPhone" cssClass="form-control" cssStyle="margin: 5px"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="brokerageFee">Phí môi giới</label>
                            <div class="col-sm-9">
                                <form:input path="brokerageFee" cssClass="form-control" cssStyle="margin: 5px"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Loại toà nhà </label>
                            <div class="col-sm-9">
                                <form:checkboxes path="type" items="${typemapsbyconstant}" cssStyle="margin: 5px"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 no-padding-right">Hình đại diện</label>
                            <input class="col-sm-3 no-padding-right" type="file" id="uploadImage"/>
                            <div class="col-sm-9">
                                <c:if test="${not empty modelEdit.image}">
                                    <c:set var="imagePath" value="/repository${modelEdit.image}"/>
                                    <img src="${imagePath}" id="viewImage" width="300px" height="300px"
                                         style="margin-top: 50px">
                                </c:if>
                                <c:if test="${empty modelEdit.image}">
                                    <img src="/admin/image/default.png" id="viewImage" width="300px" height="300px">
                                </c:if>
                            </div>
                        </div>
                        <input type="hidden" id="buildingId" name="buildingId" value="${modelEdit.id}">
                        <div class="form-group">
                            <div class="col-sm-9">
                                <button type="button" class="btn btn-primary" id="btnAddBuilding">
                                        ${modelEdit.id == null ? 'Thêm toà nhà' : 'Cập nhật toà nhà'}
                                </button>
                                <button type="button" class="btn btn-primary"
                                        onclick="window.location.href='<c:url value='/admin/building-list'/>'">Quay lại
                                </button>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div><!-- /.row -->
        </div><!-- /.page-content -->
    </div>
</div><!-- /.main-content -->

<script>

    var imageBase64 = '';
    var imageName = '';

    $("#submitBtn").click(function () {
        var data = {};
        var formData = $("#formEdit").serializeArray();
        $.each(formData, function (i, e) {
            if ('' !== e.value && null != e.value) {
                data['' + e.name + ''] = e.value;
            }

            if ('' !== imageBase64) {
                data['imageBase64'] = imageBase64;
                data['imageName'] = imageName;
            }
        });
        // var buildingId = data['id'];

        $('#loading_image').show();

        $.ajax({
            type: "POST",
            url: "${buildingAPI}",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function (res) {
                $('#loading_image').hide();
                showMessageConfirmation("Thành công", "Thao tác thành công!", "success", "/admin/building-edit-" + res.id);
            },
            error: function () {
                $('#loading_image').hide();
                var redirectUrl = (null === buildingId) ? "" : "/admin/building-edit-" + {buildingId};
                showMessageConfirmation("Thất bại", "Đã có lỗi xảy ra! Vui lòng kiểm tra lại.", "warning", redirectUrl);
            }
        });
    });

    $("#cancelBtn").click(function () {
        showAlertBeforeCancelForm(function () {
            window.location.href = '/admin/building-list';
        })
    });

    $('#uploadImage').change(function (event) {
        var reader = new FileReader();
        var file = $(this)[0].files[0];
        reader.onload = function (e) {
            imageBase64 = e.target.result;
            imageName = file.name;
        };
        reader.readAsDataURL(file);
        openImage(this, "viewImage");
    });

    function openImage(input, imageView) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#' + imageView).attr('src', reader.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

    // #################
    $('#btnAddBuilding').click(function (e) {
        e.preventDefault();
        const data = {};
        const buildingTypes = [];
        const formData = $('#formEdit').serializeArray();
        $.each(formData, function (index, v) {
            if ('type' === v.name) {
                buildingTypes.push(v.value);
            } else {
                data["" + v.name + ""] = v.value;
            }
            if ('' !== imageBase64) {
                data['imageBase64'] = imageBase64;
                data['imageName'] = imageName;
            }
        });
        data['type'] = buildingTypes;
        let buildingId = $('#buildingId').val();
        data['id'] = buildingId;
        //updateBuilding(data,buildingId);
        save(data, buildingId);
    });

    function updateBuilding(data, buildingId) {
        $.ajax({
            type: 'POST',
            url: "${buildingAPI}/" + buildingId,
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                console.log('success');
            },
            error: function (response) {
                console.log('failed');
                console.log(response);
            }
        });
    }

    // ################
    function save(data, id) {
        $.ajax({
            type: 'POST',
            url: "${buildingAPI}",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                console.log('success');
                //window.location.href = "<c:url value='/admin/building-edit-"+response.id+"?message=update_success'/>";
                window.location.href = "<c:url value='/admin/building-edit-"+response.id+"?message=update_success'/>";
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

    // #################

    <%--$('#btnAddBuilding').click(function (e){--%>
    <%--    e.preventDefault();--%>
    <%--    const data = {};--%>
    <%--    const buildingTypes = [];--%>
    <%--    const formData = $('#formEdit').serializeArray();--%>
    <%--    $.each(formData, function(index,v){--%>
    <%--        if ('type' === v.name) {--%>
    <%--            buildingTypes.push(v.value);--%>
    <%--        } else {--%>
    <%--            data["" + v.name + ""] = v.value;--%>
    <%--        }--%>
    <%--    });--%>
    <%--    data['type'] = buildingTypes;--%>
    <%--    let buildingId = $('#buildingId').val();--%>
    <%--    // if('' === buildingId){--%>
    <%--    //     addBuilding(data);--%>
    <%--    // }else{--%>
    <%--    //     updateBuilding(data,buildingId);--%>
    <%--    // }--%>
    <%--    updateBuilding(data,buildingId);--%>
    <%--});--%>
    <%--function updateBuilding(data, buildingId){--%>
    <%--    $.ajax({--%>
    <%--        type: 'POST',--%>
    <%--        url: "${buildingAPI}/"+buildingId,--%>
    <%--        data: JSON.stringify(data),--%>
    <%--        dataType: "json",--%>
    <%--        contentType : "application/json",--%>
    <%--        success: function (response) {--%>
    <%--            console.log('success');--%>
    <%--        },--%>
    <%--        error: function(response){--%>
    <%--            console.log('failed');--%>
    <%--            console.log(response);--%>
    <%--        }--%>
    <%--    });--%>
    <%--}--%>


</script>
</body>
</html>
