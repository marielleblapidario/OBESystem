<!DOCTYPE html>
<jsp:include page ="security.jsp" />
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>AdminLTE 2 | Data Tables</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!-- Bootstrap 3.3.6 -->
        <link rel="stylesheet" href="/OBESystem/resources/bootstrap/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="/OBESystem/resources/downloads/font-awesome-4.7.0/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="/OBESystem/resources/downloads/ionicons-2.0.1/css/ionicons.min.css">
        <!-- DataTables -->
        <link rel="stylesheet" href="/OBESystem/resources/plugins/datatables/dataTables.bootstrap.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="/OBESystem/resources/dist/css/AdminLTE.min.css">
        <!-- AdminLTE Skins. Choose a skin from the css/skins
             folder instead of downloading all of them to reduce the load. -->
        <link rel="stylesheet" href="/OBESystem/resources/dist/css/skins/_all-skins.min.css">
        <link rel="stylesheet" href="/OBESystem/js/background.css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body class="hold-transition skin-blue sidebar-mini">
        <!-- ./wrapper -->
        <div class="wrapper">
            <div class="content-wrapper">
                <!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="col-xs-12">
                            <!-- /.box -->
                            <div class="box box-success">
                                <div class="box-header">
                                    <h3 class="box-title">View Programs List</h3>
                                    <br>
                                    <a href="/OBESystem/ViewCreateProgram"><button id="button-new-program" type="button" class="btn btn-success pull-right"><i class="fa fa-plus"></i> New Program</button></a>

                                </div>

                                <!-- /.box-header -->
                                <div class="box-body">
                                    <table id="example1" class="table table-bordered table-striped">
                                        <thead>
                                            <tr>
                                                <th>Code</th>
                                                <th>Program</th>
                                                <th>College</th>
                                                <th>Contributor</th>
                                                <th>Tools</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                </div>
                                <!-- /.box-body -->
                            </div>
                            <!-- /.box -->
                        </div>
                        <!-- /.col -->
                    </div>
                    <!-- /.row -->
                </section>
            </div>
        </div>
        <!-- jQuery 2.2.3 -->
        <script src="/OBESystem/resources/plugins/jQuery/jquery-2.2.3.min.js"></script>
        <!-- Bootstrap 3.3.6 -->
        <script src="/OBESystem/resources/bootstrap/js/bootstrap.min.js"></script>
        <!-- DataTables -->
        <script src="/OBESystem/resources/plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="/OBESystem/resources/plugins/datatables/dataTables.bootstrap.min.js"></script>
        <!-- SlimScroll -->
        <script src="/OBESystem/resources/plugins/slimScroll/jquery.slimscroll.min.js"></script>
        <!-- FastClick -->
        <script src="/OBESystem/resources/plugins/fastclick/fastclick.js"></script>
        <!-- AdminLTE App -->
        <script src="/OBESystem/resources/dist/js/app.min.js"></script>
        <!-- AdminLTE for demo purposes -->
        <script src="/OBESystem/resources/dist/js/demo.js"></script>
        <!-- page script -->
        <script src="/OBESystem/js/view_program_list.js"></script>
    </body>
</html>
