<!DOCTYPE html>
<html>
<head>
  <title></title>
  <script type="text/javascript">
    $(document).ready(function(){
        //Handles menu drop down
        $('.dropdown-menu').find('form').click(loginProcess)
    });    

  function loginProcess(e) {
    e.preventDefault();
    console.log("click");
    
    var queryString = $(".form").serialize();
    var url = './facebook';
    console.log("query : " + queryString);
    console.log("url : " + url);
    
    $.ajax({
      type:'post',
      url:url,
      data:queryString,
      dataType:'json',
      error:onError,
      sucess:onSucess});
  }

  function onError() {
    console.log("onError");
  }

  function onSucess(data,status) {
    console.log("data : "+ data);
    console.log("status : "+ status);
  }

  </script>
</head>
<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#">Logo</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Home</a></li>
        <li><a href="#">About</a></li>
        <li><a href="#">Projects</a></li>
        <li><a href="#">Contact</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <!--
        <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
        -->
                  <li><a href="./facebook">Sign Up</a></li>
                  <li class="dropdown">
                     <a href="http://www.jquery2dotnet.com" class="dropdown-toggle" data-toggle="dropdown">Sign in <b class="caret"></b></a>
                     <ul class="dropdown-menu" style="padding: 15px;min-width: 250px;">
                        <li>
                           <div class="row">
                              <div class="col-md-12">
                                 <form class="form" role="form" method="post" action="login" accept-charset="UTF-8" id="login-nav">
                                    <div class="form-group">
                                       <label class="sr-only" for="exampleInputEmail2">Email address</label>
                                       <input type="email" class="form-control" id="exampleInputEmail2" placeholder="Email address" value="aaa" required>
                                    </div>
                                    <div class="form-group">
                                       <label class="sr-only" for="exampleInputPassword2">Password</label>
                                       <input type="password" class="form-control" id="exampleInputPassword2" placeholder="Password" value="bbb" required>
                                    </div>
                                    <div class="checkbox">
                                       <label>
                                       <input type="checkbox"> Remember me
                                       </label>
                                    </div>
                                    <div class="form-group">
                                       <button type="submit" class="btn btn-success btn-block">Sign in</button>
                                    </div>
                                 </form>
                              </div>
                           </div>
                        </li>
                        <li class="divider"></li>
                        <li>
                           <input class="btn btn-primary btn-block" type="button" id="sign-in-google" value="Sign In with Google">
                           <input class="btn btn-primary btn-block" type="button" id="sign-in-twitter" value="Sign In with Twitter">
                        </li>
                     </ul>
                  </li>
      </ul>
    </div>
  </div>
</nav>
</body>
</html>
