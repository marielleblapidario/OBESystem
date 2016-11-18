<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>AdminLTE 2 | Simple Tables</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!-- Bootstrap 3.3.6 -->
        <link rel="stylesheet" href="/OBESystem/resources/bootstrap/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="/OBESystem/resources/downloads/font-awesome-4.7.0/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="/OBESystem/resources/downloads/ionicons-2.0.1/css/ionicons.min.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="/OBESystem/resources/dist/css/AdminLTE.min.css">
        <!-- AdminLTE Skins. Choose a skin from the css/skins
             folder instead of downloading all of them to reduce the load. -->
        <link rel="stylesheet" href="/OBESystem/resources/dist/css/skins/_all-skins.min.css">
        <!--pace-->
        <link rel="stylesheet" href="/OBESystem/resources/plugins/pace/pace.css">
        <script src="/OBESystem/resources/plugins/pace/pace.js"></script>
    </head>
    <div class="hold-transition skin-green sidebar-mini">

        <header class="main-header">

            <!-- Logo -->
            <a href="index2.html" class="logo">
                <!-- mini logo for sidebar mini 50x50 pixels -->
                <span class="logo-mini"><b>O</b>BE</span>
                <!-- logo for regular state and mobile devices -->
                <span class="logo-lg"><b>OBE</b>System</span>
            </a>

            <!-- Header Navbar: style can be found in header.less -->
            <nav class="navbar navbar-static-top">
                <!-- Sidebar toggle button-->
                <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                    <span class="sr-only">Toggle navigation</span>
                </a>
                <!-- Navbar Right Menu -->
                <div class="navbar-custom-menu">
                    <ul class="nav navbar-nav">
                        <!-- User Account: style can be found in dropdown.less -->
                        <li class="dropdown user user-menu">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <img src="/OBESystem/resources/dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
                                <span class="hidden-xs">${login.fullName}</span>
                            </a>
                            <ul class="dropdown-menu">
                                <!-- User image -->
                                <li class="user-header">
                                    <img src="/OBESystem/resources/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">

                                    <p>
                                        ${login.fullName}
                                        <small>${login.position}</small>
                                    </p>
                                </li>
                                <!-- Menu Footer-->
                                <li class="user-footer">
                                    <form action="Logout" method="post" name="Logout">
                                        <button type = "submit" id="logout" class="btn btn-default btn-flat btn-block">Sign out</button>
                                    </form>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>

            </nav>
        </header>

        <nav class ="navbar-left">
            <aside class="main-sidebar">
                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">
                    <!-- sidebar menu: : style can be found in sidebar.less -->
                    <ul class="sidebar-menu">
                        <li class="header">MAIN NAVIGATION</li>
          
                        <li>
                            <a href="/OBESystem/ViewIGA"><i class="fa  fa-institution"></i> <span>Institutional Graduate Attribute</span></a>
                        </li>
                        <li class="treeview">
                            <a href="#">
                                <i class="fa fa-graduation-cap"></i> <span>Programs</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="/OBESystem/RedirectToSearchMapping"><i class="fa fa-circle-o"></i>Mapping Summary</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="/OBESystem/ViewCurriculumList"><i class="fa fa-file-text"></i> <span>Curriculum</span></a>
                        </li>
                        <li class="treeview">
                            <a href="#">
                                <i class="fa fa-flask"></i> <span>Course</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="/OBESystem/ViewCourseList"><i class="fa fa-circle-o"></i>Course List</a></li>
                                <li><a href="/OBESystem/RedirectToViewSyllabusList"><i class="fa fa-circle-o"></i>Syllabus List</a></li>
                                <li><a href="/OBESystem/RedirectToOfferingsList"><i class="fa fa-circle-o"></i>Courses for Enrollment List</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="/OBESystem/RedirectToPowerBI"><i class="fa fa-bar-chart"></i> <span>Dashboard</span></a>
                        </li>
                    </ul>
                </section>
                <!-- /.sidebar -->
            </aside>
        </nav>
    </div>
</html>
