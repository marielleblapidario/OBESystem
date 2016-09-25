
<%@include file ="navbar.jsp"%>
<%@include file ="/bootstrap/css/bootstrap.min.css"%>
<%@include file ="/dist/css/AdminLTE.min.css"%>
<%@include file ="/dist/css/skins/_all-skins.min.css"%>
<%@include file ="/plugins/jQuery/jquery-2.2.3.min.js"%>
<%@include file ="/bootstrap/js/bootstrap.min.js"%>
<%@include file ="/plugins/slimScroll/jquery.slimscroll.min.js"%>
<%@include file ="/plugins/fastclick/fastclick.js"%>
<%@include file ="/dist/js/app.min.js"%>
<%@include file ="/dist/js/demo.js"%>

<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>AdminLTE 2 | Simple Tables</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!-- Bootstrap 3.3.6 -->
        <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="/dist/css/AdminLTE.min.css">
        <!-- AdminLTE Skins. Choose a skin from the css/skins
             folder instead of downloading all of them to reduce the load. -->
        <link rel="stylesheet" href="/dist/css/skins/_all-skins.min.css">
    </head>
    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">

                <!-- Main content -->
                <section class="content">
                    <div class="box box-info">
                        <!-- /.box-header -->
                        <div class="box-header">
                            <h3 class="box-title">Institutional Graduate Attribute</h3><br>
                        </div>
                        <div class="box-body table-responsive">
                            <table class="table table-hover">
                                <tr>
                                    <th>Code</th>
                                    <th>
                                        <div class="col-sm-10">
                                            Institutional Graduate Attribute
                                        </div>
                                    </th>
                                    <th>
                                        <div class="col-sm-10">
                                            Remarks
                                        </div>
                                    </th>
                                    <th>Tools</th>
                                </tr>
                                <tr>
                                    <td>IGA01</td>
                                    <td>
                                        <div class="col-sm-10">
                                            <input type="email" class="form-control no-border" id="inputEmail3" placeholder="Enter Institutional Graduate Attribute">
                                        </div> 
                                    </td>
                                    <td>
                                        <div class="col-sm-10">
                                            <input type="email" class="form-control no-border" id="inputEmail3">
                                        </div>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-success btn-xs"><i class="fa fa-edit"></i></button>
                                        <button type="button" class="btn btn-danger btn-xs"><i class="fa fa-trash"></i></button>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="box-footer">  
                            <button type="button" class="btn btn-primary pull-left"><i class="fa fa-plus"></i> Add Row</button>
                            <button type="submit" class="btn btn-default pull-right">Cancel</button>
                            <button type="submit" class="btn btn-success pull-right">Save</button>
                        </div>
                        <!-- /.box-footer -->
                    </div>
                </section>
                <!-- /.content -->
            </div>
            <!-- /.content-wrapper -->
        </div>
        <!-- ./wrapper -->

        <!-- jQuery 2.2.3 -->
        <script src="/plugins/jQuery/jquery-2.2.3.min.js"></script>
        <!-- Bootstrap 3.3.6 -->
        <script src="/bootstrap/js/bootstrap.min.js"></script>
        <!-- Slimscroll -->
        <script src="/plugins/slimScroll/jquery.slimscroll.min.js"></script>
        <!-- FastClick -->
        <script src="/plugins/fastclick/fastclick.js"></script>
        <!-- AdminLTE App -->
        <script src="/dist/js/app.min.js"></script>
        <!-- AdminLTE for demo purposes -->
        <script src="/dist/js/demo.js"></script>
    </body>
</html>
