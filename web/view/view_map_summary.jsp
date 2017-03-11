<!DOCTYPE html>
<jsp:include page ="security.jsp" />
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
        <link rel="stylesheet" href="/OBESystem/js/background.css">
        <link rel="stylesheet" href="/OBESystem/js/mapping_printing.css"  media="print">
    </head>
    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">

                <!-- Main content -->
                <form action="EncodeCO" method="post" name="EncodeCO">
                    <input type="hidden" name="contributor" class="readonlyWhite" id="contributor" value="${login.userID}" />
                    <section class="content">
                        <div class="box box-success">
                            <!-- /.box-header -->
                            <div class="box-header">
                                <div id="printheader">
                                    <img src="/OBESystem/resources/dist/img/dlsuLogo.png">
                                    <img src="/OBESystem/resources/dist/img/ccsLogo.png">
                                    <br>
                                </div>
                                <h3 class="box-title">Map Summary</h3><br>
                                <h5>Program: <span id = "program-title"></span></h5>
                                <input class="hidden" id="hidden-program-title" name="program-title">
                                <h5>College: <span id = "college-title" ></span></h5>
                                <input class="hidden" id="hidden-college-title" name="college-title">
                                <br>
                            </div>
                            <div class="box-body">
                                <table id="data" class="table table-bordered">
                                    <div>

                                    </div>
                                    <thead>
                                        <tr>
                                            <th>IGA</th>
                                            <th>PA</th>
                                            <th>PO</th>
                                            <th>PI</th>
                                        </tr>
                                    </thead>
                                    <tbody></tbody>
                                </table>
                            </div>
                            <div class="box-footer">
                                <a href="/OBESystem/RedirectToSearchMapping"><button type="button" class="btn btn-default pull-right">Back</button></a>
                                <button type="button" id="button-print" class="btn btn-success pull-right"><i class="fa fa-print"></i>  Print</button>
                            </div>
                            <!-- /.box-footer -->
                        </div>
                    </section>
                </form>
                <!-- /.content -->
            </div>
            <!-- /.content-wrapper -->
        </div>
        <!-- ./wrapper -->

        <!-- jQuery 2.2.3 -->
        <script src="/OBESystem/resources/plugins/jQuery/jquery-2.2.3.min.js"></script>
        <!-- Bootstrap 3.3.6 -->
        <script src="/OBESystem/resources/bootstrap/js/bootstrap.min.js"></script>
        <!-- Slimscroll -->
        <script src="/OBESystem/resources/plugins/slimScroll/jquery.slimscroll.min.js"></script>
        <!-- FastClick -->
        <script src="/OBESystem/resources/plugins/fastclick/fastclick.js"></script>
        <!-- AdminLTE App -->
        <script src="/OBESystem/resources/dist/js/app.min.js"></script>
        <!-- AdminLTE for demo purposes -->
        <script src="/OBESystem/resources/dist/js/demo.js"></script>
        <!--selfmade-->
        <script src="/OBESystem/js/store_program_search.js"></script>
        <script src="/OBESystem/js/view_map_summary.js"></script>
    </body>
</html>
