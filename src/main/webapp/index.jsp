<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>
<div class="card">
<h4 class="card-title">Add User</h4>
<div class="card-body">
<form action="table" method="post" class="form">
<div class="form-group">
<label class="col-3">Id:</label>
<input class="form-control col-9" type="text" name="id"/>
</div>
<div class="form-group">
<label class="col-3">Name:</label>
<input class="form-control col-9" type="text" name="name"/>
</div>

  <div class="form-group">
  <label class="col-3">Age:</label>
  <input class="form-control col-9" type="integer" name="age"/>
  </div>
  <div class="form-group">
    <label class="col-3">City:</label>
    <input class="form-control col-9" type="text" name="city"/>
    </div>
<button type="submit" value="submit" class="btn btn-primary"> Submit</button>

</form>
</div>
</div>

     <a href="delete.jsp" >Delete</a>
       <a href="update.jsp" type="submit" >Update</a>

</body>
</html>
