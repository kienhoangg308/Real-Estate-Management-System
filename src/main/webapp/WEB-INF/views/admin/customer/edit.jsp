<%--
  Created by IntelliJ IDEA.
  User: hoangtrungkien
  Date: 26/02/2023
  Time: 22:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
<c:url var="customerEditURL" value="/api/customer-edit"/>
<c:url var="customerAPIURL" value="/api/customer"/>
<c:url var="transactionURL" value="/api/customer/transaction"/>
<html>
<head>
    <title>Chỉnh sửa khách hàng</title>
</head>
<body>
<div class="main-content">
  <div class="main-content-inner">
    <div class="breadcrumbs" id="breadcrumbs">
      <script type="text/javascript">
        try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
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
          <form:form commandName="modelEdit" action="${customerEditURL}" id="formEdit" method="POST">
            <%--                    <form class="form-horizontal" role="form" id="formEdit">--%>
            <div class="form-group">
              <label class="col-sm-3 control-label no-padding-right" for="fullName"> Tên đầy đủ </label>
              <div class="col-sm-9">
                  <form:input path="fullName" cssClass="form-control" cssStyle="margin: 5px"/>
              </div>
            </div>
            <div class="space-4"></div>
            <div class="form-group">
              <label class="col-sm-3 control-label no-padding-right" for="phone"> Số điện thoại </label>
              <div class="col-sm-9">
                <form:input path="phone" cssClass="form-control" cssStyle="margin: 5px"/>
              </div>
            </div>
            <div class="space-4"></div>
            <div class="form-group">
              <label class="col-sm-3 control-label no-padding-right" for="email"> Email </label>
              <div class="col-sm-9">
                <form:input path="email" cssClass="form-control" cssStyle="margin: 5px"/>
              </div>
            </div>
            <div class="space-4"></div>
            <div class="form-group">
              <label class="col-sm-3 control-label no-padding-right" for="companyName"> Tên công ty </label>
              <div class="col-sm-9">
                <form:input path="companyName" cssClass="form-control" cssStyle="margin: 5px"/>
              </div>
            </div>
            <div class="space-4"></div>
            <div class="form-group">
              <label class="col-sm-3 control-label no-padding-right" for="demand"> Nhu cầu </label>
              <div class="col-sm-9">
                <form:input path="demand" cssClass="form-control" cssStyle="margin: 5px"/>
              </div>
            </div>
            <div class="space-4"></div>
            <div class="form-group">
              <label class="col-sm-3 control-label no-padding-right" for="note"> Ghi chú </label>
              <div class="col-sm-9">
                <form:input path="note" cssClass="form-control" cssStyle="margin: 5px"/>
              </div>
            </div>
            <div class="space-4"></div>
            <div class="form-group">
              <div class="col-sm-9">
                <button type="button" class="btn btn-primary" id="btnSaveCustomer" >
                    ${modelEdit.id == null ? 'Thêm khách hàng' : 'Cập nhật khách hàng'}
                </button>
                <button type="button" class="btn btn-primary" onclick="window.location.href='<c:url value='/admin/customer-list'/>'">Quay lại</button>
              </div>
            </div>
            <input type="hidden" id="customerId" name="customerId" value="${modelEdit.id}">

          </form:form>
        </div>

        <br>
        <br>
        <c:forEach items="${transactions}" var="transaction">
          <br>
          <div class="row">
            <div class="col-xs-12">
              <h4 style="display: inline-block;color:#3a87ad">
                  ${transaction.name}
                <button type="button" class="ace-icon fa fa-pencil bigger-120" data-toggle="tooltip"
                        onClick="addTransaction(${transaction.code})" title="Thêm giao dịch">
                </button>
              </h4>
              <table class="table table-striped table-bordered table-hover">
                <thead>
                <tr>
                  <th>Ngày tạo</th>
                  <th>Ghi chú</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${transaction.transaction}">
                  <tr>
                    <td>${item.date}</td>
                    <td>${item.note}</td>
                  </tr>
                </c:forEach>
                <tr>
                  <td></td>
                  <td>
                    <form id ="${transaction.code}">
                      <input type ="hidden" name="code" value="${transaction.code}">
                      <input type ="hidden" name="customerId" value="${modelEdit.id}">
                      <input class="form-control" name="note">
                    </form>
                  </td>
                </tr>
                </tbody>
              </table>
            </div>
          </div>
        </c:forEach>
      </div><!-- /.row -->
      <br/>
    </div><!-- /.page-content -->
  </div>
</div><!-- /.main-content -->

<script>

  <%--$('#btnAddTransaction').click(function (e){--%>
  <%--  e.preventDefault();--%>
  <%--  var data = {};--%>
  <%--  let note = $('#transactionNote').val();--%>
  <%--  let customerId = $('#customerId').val();--%>
  <%--  let transactionCode = $('table').attr('id');--%>
  <%--  data['code'] = transactionCode;--%>
  <%--  data['customerId'] = customerId;--%>
  <%--  data['note'] = note;--%>
  <%--  $.ajax({--%>
  <%--    type: 'POST',--%>
  <%--    url: "${transactionURL}",--%>
  <%--    data: JSON.stringify(data),--%>
  <%--    dataType: "json",--%>
  <%--    contentType : "application/json",--%>
  <%--    success: function (response) {--%>
  <%--      console.log('success');--%>
  <%--    },--%>
  <%--    error: function(response){--%>
  <%--      console.log('failed');--%>
  <%--      console.log(response);--%>
  <%--    }--%>
  <%--  });--%>
  <%--});--%>

  function addTransaction(formId){
    // let note = $('#transactionNote').val();
    // let customerId = $('#customerId').val();
    // var data = {};
    // data['code'] = transactionCode;
    // data['customerId'] = customerId;
    // data['note'] = note;
    // saveTransaction(data,customerId);
    const data={};
    const formData = $(formId).serializeArray();
    $.each(formData, function(indexInArray,element){
      data[""+element.name+""] = element.value;
    });
    let customerId = data['customerId'];
    saveTransaction(data, customerId);
  }

  function saveTransaction(data, customerId){
    $.ajax({
      type: "POST",
      url: "${transactionURL}",
      data: JSON.stringify(data),
      dataType: "json",
      contentType: "application/json",
      success: function (response) {
        console.log('success');
        window.location.href = "<c:url value='/admin/customer-edit-"+customerId+"?message=update_success'/>";
      },
      error: function (response) {
        console.log('failed');
        console.log(response);
      }
    });
  }

  $('#btnAddRow').click(function (e) {
    e.preventDefault();
    
  });

  $('#btnSaveCustomer').click(function (e){
      e.preventDefault();
      const data = {};
      const buildingTypes = [];
      const formData = $('#formEdit').serializeArray();
      // $.each(formData, function(index,v){
      //   if ('type' === v.name) {
      //     buildingTypes.push(v.value);
      //   } else {
      //     data["" + v.name + ""] = v.value;
      //   }
      $.each(formData, function(index,v){
          data[""+v.name+""] = v.value;
      });

      let customerId = $('#customerId').val();
      data['id'] = customerId;
      //updateBuilding(data,buildingId);
      save(data, customerId);
  });

  function save(data,id){
    $.ajax({
      type: 'POST',
      url: "${customerAPIURL}",
      data: JSON.stringify(data),
      dataType: "json",
      contentType : "application/json",
      success: function (response) {
        console.log('success');
        window.location.href = "<c:url value='/admin/customer-edit-"+response.id+"?message=update_success'/>";
      },
      error: function(response){
        console.log('failed');
        console.log(response);
      }
    });
  }

  function reload(){
    window.location.href = "${customerEditURL}";
  }


</script>

</body>
</html>
