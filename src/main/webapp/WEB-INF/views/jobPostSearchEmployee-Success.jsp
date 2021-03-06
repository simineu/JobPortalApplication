<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <title>Job Portal</title>

    <!-- Bootstrap Core CSS -->
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${contextPath}/resources/css/landing-page.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="${contextPath}/resources/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-fixed-top topnav" role="navigation">
        <div class="container topnav">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand topnav" href="#">Job Portal</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
				
                    <li>
                        <a href="${contextPath}/employee/">Home</a>
                    </li>
					<li>
                        <a href="${contextPath}/user/logout">Logout</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>


    <!-- Header -->
    <a name="about"></a>
    <div class="intro-header">
        <div class="container">

            <div class="row">
                <div class="col-lg-12">
                    <div class="intro-message">
                        <h1>View Applied Jobs</h1>
                        <h3>Get Connected With</h3>
                        <hr class="intro-divider">
                        <ul class="list-inline intro-social-buttons">
                            <li>
                                <a href="https://twitter.com/" class="btn btn-default btn-lg"><i class="fa fa-twitter fa-fw"></i> <span class="network-name">Twitter</span></a>
                            </li>
                            <li>
                                <a href="https://github.com/" class="btn btn-default btn-lg"><i class="fa fa-github fa-fw"></i> <span class="network-name">Github</span></a>
                            </li>
                            <li>
                                <a href="https://www.linkedin.com/uas/login?goback=&trk=hb_signin" class="btn btn-default btn-lg"><i class="fa fa-linkedin fa-fw"></i> <span class="network-name">Linkedin</span></a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>

        </div>
        <!-- /.container -->

    </div>
    <!-- /.intro-header -->

    <!-- Page Content -->

	<a  name="services"></a>
    <div class="content-section-a">

        <div class="container">
            <div class="row">
               <h2>Applied Jobs</h2>

	<table cellpadding="5" cellspacing="5">
		<c:forEach var="j" items="${jobPost}">
		<tr><td><br/></td></tr>
			<tr>
				<td><b>Job ID: </b></td>
				<td>${j.jobID}</td>
			</tr>
			<tr><td><br/></td></tr>
			<tr>
				<td><b>Job Title: </b></td>
				<td>${j.jobTitle}</td>
			</tr>
			<tr><td><br/></td></tr>
			<tr>
				<td><b>Company Name: </b></td>
				<td>${j.companyName}</td>
			</tr>
			<tr><td><br/></td></tr>
			<tr>
				<td><b>Location: </b></td>
				<td>${j.location}</td>
			</tr>
			<tr><td><br/></td></tr>
			<tr>
				<td><b>Job Type: </b></td>
				<td>${j.type}</td>
			</tr>
			<tr><td><br/></td></tr>
			<tr>
				<td><b>Description: </b></td>
				<td>${j.description}</td>
			</tr>
			<tr><td><br/></td></tr>
			<tr>
				<td><b>Duration: </b></td>
				<td>${j.duration}</td>
			</tr>
			<tr><td><br/></td></tr>
			<tr>
				<td><b>Number Of Positions: </b></td>
				<td>${j.noOfPosition}</td>
			</tr>
			<tr><td><br/></td></tr>
			<tr>
				<td><b>Posted On: </b></td>
				<td>${j.postedOn}</td>
			</tr>
			<tr><td><br/></td></tr>
			<tr><td colspan="2"><a href="${contextPath}/employee/apply.htm?ID=${j.ID}&jobID=${j.jobID}&jobTitle=${j.jobTitle}&location=${j.location}
			&companyName=${j.companyName}&type=${j.type}&description=${j.description}&duration=${j.duration}&noOfPosition=${j.noOfPosition}
			&postedOn=${j.postedOn}">Apply</a></td></tr>
			<tr><td colspan="2"><br/></td></tr>
		</c:forEach>
	</table>

            </div>

        </div>
        <!-- /.container -->

    </div>
    <!-- /.content-section-a -->

   
    
	
	
   

    <!-- jQuery -->
    <script src="${contextPath}/resources/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>

</html>
