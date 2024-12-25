<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>
<body>
    <div class="d-flex" style="height: 100vh">
	    <%@ include file="sidebar.jsp" %>
	    <div class="flex-grow-1">
    		<%@ include file="header.jsp" %>
	     	<div class="border rounded m-4 p-4">
	     	<form acition="${pageContext.request.contextPath}/add-book" method="POST">
			  <div class="form-group mb-2">
			    <label for="name">Tên sách</label>
			    <input type="text" class="form-control" id="name" name="name">
			  </div>
			  <div class="form-group mb-2">
			    <label for="category">Thể loại sách </label>
			    <select class="form-control" id="category" name="category">
			    	<!-- Thêm các thể loại sách ở đây -->
			      	<option value="Văn học">Văn học</option>
			      	<option value="Toán học">Toán học</option>
			      	<option value="Khoa học">Khoc học</option>
			    </select>
			  </div>
			  <div class="form-group mb-2">
			    <label for="author">Tác giả</label>
			    <input type="text" class="form-control" id="author" name="author">
			  </div>
			  <div class="form-group mb-2">
			    <label for="description">Mô tả</label>
			    <textarea class="form-control" name="description" id="description" rows="3"></textarea>
			  </div>
			  <div class="form-group mb-2">
			    <label for="amount">Số lượng</label>
			    <input type="number" class="form-control" id="amount" name="amount">
			  </div>
			  <div>
			  	<button class="btn btn-secondary">Hủy</button>
			  	<button class="btn btn-success">Lưu</button>
			  </div>
			</form>
	     	</div>
	    </div>
    </div>
</body>
<%@ include file="footer.jsp" %>