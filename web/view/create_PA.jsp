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
        <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
        <!-- Theme style -->
        <!-- daterange picker -->
        <link rel="stylesheet" href="resources/plugins/daterangepicker/daterangepicker.css">
        <!-- bootstrap datepicker -->
        <link rel="stylesheet" href="resources/plugins/datepicker/datepicker3.css">
        <!-- iCheck for checkboxes and radio inputs -->
        <link rel="stylesheet" href="resources/plugins/iCheck/all.css">
        <!-- Bootstrap Color Picker -->
        <link rel="stylesheet" href="resources/plugins/colorpicker/bootstrap-colorpicker.min.css">
        <!-- Bootstrap time Picker -->
        <link rel="stylesheet" href="resources/plugins/timepicker/bootstrap-timepicker.min.css">
        <!-- Select2 -->
        <link rel="stylesheet" href="resources/plugins/select2/select2.min.css">
        <link rel="stylesheet" href="resources/dist/css/AdminLTE.min.css">
        <!-- AdminLTE Skins. Choose a skin from the css/skins
             folder instead of downloading all of them to reduce the load. -->
        <link rel="stylesheet" href="resources/dist/css/skins/_all-skins.min.css">
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
                            <h3 class="box-title">Program Attribute</h3><br>
                            <h5>Program: BS Information System</h5>
                            <h5>College: College of Computer Studies</h5>
                            <br>
                            
                            <label class="col-sm-2 control-label">Approver</label>
                            <div class="col-sm-10">
                                <select class="form-control select2 select2-hidden-accessible" style="width: 30%;" tabindex="-1" aria-hidden="true">
                                    <option selected="selected">Mr. Danny Cheng</option>
                                    <option>Mr. Oliver Malabanan</option>
                                </select>
                            </div>
                            <br>
                        </div>
                        <div class="box-body table-responsive">
                            <table class="table table-hover">
                                <tr>
                                    <th>Code</th>
                                    <th>
                                        <div class="col-sm-10">
                                            Program Attribute
                                        </div>
                                    </th>
                                    <th>
                                        <div class="col-sm-10">
                                            Status
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
                                    <td>PA01</td>
                                    <td>
                                        <div class="col-sm-10">
                                            <input type="email" class="form-control no-border" id="inputEmail3" placeholder="Program Attribute">
                                        </div> 
                                    </td>
                                    <td>
                                        <span class="label label-success">approved</span>
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
                            <br>
                        </div>
                        <div class="box-footer">  
                            <button type="button" class="btn btn-primary pull-left"><i class="fa fa-plus"></i> Add Row</button>
                            <button type="submit" class="btn btn-default pull-right">Cancel</button>
                            <button type="submit" class="btn bg-green pull-right">Send for Approval</button>
                            <button type="submit" class="btn bg-light-blue pull-right">Save</button>
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
        <script src="resources/plugins/jQuery/jquery-2.2.3.min.js"></script>
        <!-- Bootstrap 3.3.6 -->
        <script src="resources/bootstrap/js/bootstrap.min.js"></script>
        <!-- Select2 -->
        <script src="resources/plugins/select2/select2.full.min.js"></script>
        <!-- InputMask -->
        <script src="resources/plugins/input-mask/jquery.inputmask.js"></script>
        <script src="resources/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
        <script src="resources/plugins/input-mask/jquery.inputmask.extensions.js"></script>
        <!-- date-range-picker -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
        <script src="plugins/daterangepicker/daterangepicker.js"></script>
        <!-- bootstrap datepicker -->
        <script src="resources/plugins/datepicker/bootstrap-datepicker.js"></script>
        <!-- bootstrap color picker -->
        <script src="resources/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
        <!-- bootstrap time picker -->
        <script src="resources/plugins/timepicker/bootstrap-timepicker.min.js"></script>
        <!-- SlimScroll 1.3.0 -->
        <script src="resources/plugins/slimScroll/jquery.slimscroll.min.js"></script>
        <!-- iCheck 1.0.1 -->
        <script src="resources/plugins/iCheck/icheck.min.js"></script>
        <!-- FastClick -->
        <script src="resources/plugins/fastclick/fastclick.js"></script>
        <!-- AdminLTE App -->
        <script src="resources/dist/js/app.min.js"></script>
        <!-- AdminLTE for demo purposes -->
        <script src="resources/dist/js/demo.js"></script>
        <!-- Page script -->
        <script>
            $(function () {
                //Initialize Select2 Elements
                $(".select2").select2();

                //Datemask dd/mm/yyyy
                $("#datemask").inputmask("dd/mm/yyyy", {"placeholder": "dd/mm/yyyy"});
                //Datemask2 mm/dd/yyyy
                $("#datemask2").inputmask("mm/dd/yyyy", {"placeholder": "mm/dd/yyyy"});
                //Money Euro
                $("[data-mask]").inputmask();

                //Date range picker
                $('#reservation').daterangepicker();
                //Date range picker with time picker
                $('#reservationtime').daterangepicker({timePicker: true, timePickerIncrement: 30, format: 'MM/DD/YYYY h:mm A'});
                //Date range as a button
                $('#daterange-btn').daterangepicker(
                        {
                            ranges: {
                                'Today': [moment(), moment()],
                                'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                                'Last 7 Days': [moment().subtract(6, 'days'), moment()],
                                'Last 30 Days': [moment().subtract(29, 'days'), moment()],
                                'This Month': [moment().startOf('month'), moment().endOf('month')],
                                'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
                            },
                            startDate: moment().subtract(29, 'days'),
                            endDate: moment()
                        },
                        function (start, end) {
                            $('#daterange-btn span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
                        }
                );

                //Date picker
                $('#datepicker').datepicker({
                    autoclose: true
                });

                //iCheck for checkbox and radio inputs
                $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
                    checkboxClass: 'icheckbox_minimal-blue',
                    radioClass: 'iradio_minimal-blue'
                });
                //Red color scheme for iCheck
                $('input[type="checkbox"].minimal-red, input[type="radio"].minimal-red').iCheck({
                    checkboxClass: 'icheckbox_minimal-red',
                    radioClass: 'iradio_minimal-red'
                });
                //Flat red color scheme for iCheck
                $('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
                    checkboxClass: 'icheckbox_flat-green',
                    radioClass: 'iradio_flat-green'
                });

                //Colorpicker
                $(".my-colorpicker1").colorpicker();
                //color picker with addon
                $(".my-colorpicker2").colorpicker();

                //Timepicker
                $(".timepicker").timepicker({
                    showInputs: false
                });
            });
        </script>
    </body>
</html>
