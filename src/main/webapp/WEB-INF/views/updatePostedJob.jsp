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
                        <a href="${contextPath}/employer/">Home</a>
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
                        <h1>Welcome To Your Job Portal</h1>
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
               <h2>Update Posted Job</h2>

	<form:form action="${contextPath}/employer/updatePostedJobs" commandName="jobPost"
		method="post">

		<table>
		<tr><td><br/></td></tr>
			<tr>
				<td>Job ID:</td>
				<td><form:input path="jobID" size="50" required="required" value="${jobPost.jobID}"/>
					<font color="red"><form:errors path="jobID" /></font></td>
			</tr>
			<tr><td><br/></td></tr>
			<tr>
				<td>Job Title:</td>
				<td><form:input path="jobTitle" size="50" required="required" value="${jobPost.jobTitle}"/>
					<font color="red"><form:errors path="jobTitle" /></font></td>
			</tr>

			<tr><td><br/></td></tr>
			<tr>
				<td>Company Name:</td>
				<td><form:input path="companyName" size="50" required="required"  value="${jobPost.companyName}"/>
					<font color="red"><form:errors path="companyName" /></font></td>
			</tr>
			<tr><td><br/></td></tr>
			<tr>
				<td>Location:</td>
				<td><form:input path="location" size="50"
						required="required" value="${jobPost.location}"/> <font color="red"><form:errors
							path="location" /></font></td>
			</tr>
			<tr><td><br/></td></tr>
			<tr>
				<td>Job Type:</td>
				<td><form:radiobutton path="type" value="${jobPost.type}"/>Full Time
					</td>
				<td><form:radiobutton path="type" value="internship"/>Internship/Co-op
					</td>
			</tr>
			<tr><td><br/></td></tr>

			<tr>
				<td>Description:</td>
				<td><form:textarea path="description" rows="5" cols="50"
						required="required" value="${jobPost.description}"/> <font color="red"><form:errors
							path="description" /></font></td>
			</tr>
			
			<tr><td><br/></td></tr>
			<tr>
				<td>Duration:</td>
				<td><form:input path="duration" size="50" required="required" value="${jobPost.duration}"/>
					<font color="red"><form:errors path="duration" /></font></td>
			</tr>
			<tr><td><br/></td></tr>
			<tr>
				<td>Number Of Position:</td>
				<td><form:input path="noOfPosition" size="50"
						required="required" value="${jobPost.noOfPosition}"/> <font color="red"><form:errors
							path="noOfPosition" /></font></td>
			</tr>
			
			<tr><td><br/></td></tr>
			
			<tr>
				<td colspan="2"><input type="submit" value="Post Job" /></td>
			</tr>
			
		</table>

	</form:form>

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
