<!DOCTYPE html>
<jsp:include page ="navbar.jsp" />
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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="/OBESystem/resources/dist/css/AdminLTE.min.css">
        <!-- AdminLTE Skins. Choose a skin from the css/skins
             folder instead of downloading all of them to reduce the load. -->
        <link rel="stylesheet" href="/OBESystem/resources/dist/css/skins/_all-skins.min.css">
    </head>
    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">

                <!-- Main content -->
                <form action="EncodeCO" method="post" name="EncodeCO">
                    <input type="hidden" name="contributor" class="readonlyWhite" id="contributor" value="${login.userID}" />
                    <section class="content">
                        <div class="box box-info">
                            <!-- /.box-header -->
                            <div class="box-header">
                                <h3 class="box-title">Course Outcome</h3><br>
                                <h5>Course: <span id="course-title"></span></h5>
                                <input class="hidden" id="hidden-codeCourse" name="codeCourse">
                                <br>
                                <br>
                            </div>
                            <div class="box-body">
                                <table id="data" class="table table-bordered">
                                    <div>
                                        
                                    </div>
                                    <tr>
                                        <th>IGA</th>
                                        <th>PA</th>
                                        <th>PO</th>
                                        <th>PI</th>
                                    </tr>
                                    <tr>
                                        <td rowspan="10">IGA 1</td>
                                        <td rowspan="6">PA 1</td>
                                        <td rowspan="6">PO 1</td>
                                        <td>PI 1.1</td>
                                    </tr>
                                    <tr>
                                        <td>PI 1.2</td>
                                    </tr>
                                    <tr>
                                        <td>PI 1.3</td>
                                    </tr>
                                    <tr>
                                        <td>PI 1.4</td>
                                    </tr>
                                    <tr>
                                        <td>PI 1.5</td>
                                    </tr>
                                    <tr>
                                        <td>PI 1.6</td>
                                    </tr>
                                    <tr>
                                        <td  rowspan="4">PA 2</td>
                                        <td  rowspan="3">PO 2</td>
                                        <td>PI 2.1</td>
                                    </tr>
                                    <tr>
                                        <td>PI 2.2</td>
                                    </tr>
                                    <tr>
                                        <td>PI 2.3</td>
                                    </tr>
                                    <tr>
                                        <td>PO 3</td>
                                        <td>PI 3.1</td>
                                    </tr>
                                </table>
                            </div>
                            <div class="box-footer">  
                                <button type="button" id="addRowButton" class="btn btn-primary pull-left"><i class="fa fa-plus"></i> Add Row</button>
                                <button type="button" class="btn btn-default pull-right">Cancel</button>
                                <button type="submit" class="btn bg-green pull-right">Send for Approval</button>
                                <button type="submit" class="btn bg-light-blue pull-right">Save</button>
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
        <script src="/OBESystem/js/view_map_summary.js"></script>
    </body>
</html>
