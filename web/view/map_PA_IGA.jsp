<!DOCTYPE html>
<jsp:include page ="navbar.jsp" />
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Map PA to IGA</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!-- Bootstrap 3.3.6 -->
        <link rel="stylesheet" href="/OBESystem/resources/bootstrap/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
        <!-- DataTables -->
        <link rel="stylesheet" href="/OBESystem/resources/plugins/datatables/dataTables.bootstrap.css">
        <link rel="stylesheet" href="/OBESystem/resources/plugins/datatables/datatable/select.dataTables.min.css">
        <!-- Theme style -->
        <!-- daterange picker -->
        <link rel="stylesheet" href="/OBESystem/resources/plugins/daterangepicker/daterangepicker.css">
        <!-- bootstrap datepicker -->
        <link rel="stylesheet" href="/OBESystem/resources/plugins/datepicker/datepicker3.css">
        <!-- iCheck for checkboxes and radio inputs -->
        <link rel="stylesheet" href="/OBESystem/resources/plugins/iCheck/all.css">
        <!-- Bootstrap Color Picker -->
        <link rel="stylesheet" href="/OBESystem/resources/plugins/colorpicker/bootstrap-colorpicker.min.css">
        <!-- Bootstrap time Picker -->
        <link rel="stylesheet" href="/OBESystem/resources/plugins/timepicker/bootstrap-timepicker.min.css">
        <!-- Select2 -->
        <link rel="stylesheet" href="/OBESystem/resources/plugins/select2/select2.min.css">
        <link rel="stylesheet" href="/OBESystem/resources/dist/css/AdminLTE.min.css">
        <!-- AdminLTE Skins. Choose a skin from the css/skins
             folder instead of downloading all of them to reduce the load. -->
        <link rel="stylesheet" href="/OBESystem/resources/dist/css/skins/_all-skins.min.css">
    </head>
    <body class="hold-transition skin-blue sidebar-mini">
        <div id="map-modal" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content ">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h3 class="modal-title">Map PA to IGA</h3>
                    </div>
                    <div class="modal-body col-sm-12">
                        <p>Please select the following Institutional Graduate Attributes for <span id="selected-PAs"></span>: </p>
                        <div class="row">
                            <div class="col-sm-6" id="checkbox-div">
                            </div>
                        </div>
                        <br>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" id="modal-close" data-dismiss="modal">CLOSE</button>
                        <button type="button" class="btn btn-success" id="modal-confirm-btn" data-dismiss="modal">CONFIRM</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="wrapper">
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">

                <!-- Main content -->
                <section class="content">
                    <div class="box box-info">
                        <!-- /.box-header -->
                        <div class="box-header">
                            <h3 class="box-title">Map Graduate Attributes to Institutional Graduate Attributes</h3><br>
                            <h5>Program: <span id = "program-title"></span></h5>
                            <input class="hidden" id="hidden-program-title" name="program-title">
                            <h5>College: <span id = "college-title" ></span></h5>
                            <input class="hidden" id="hidden-college-title" name="college-title">
                            <br>
                            <label class="col-sm-2 control-label">Approver</label>
                            <div class="col-sm-10">
                                <select name="select-approver" id = "select-approver" class="form-control select2 select2-hidden-accessible" style="width: 30%;" tabindex="-1" aria-hidden="true">
                                </select>
                            </div>
                            <br>
                        </div>
                        <div class="box-body table-responsive">
                            <table id="map-table" class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Program Attribute</th>
                                        <th>Institutional Graduate Attribute</th>
                                        <th>Status</th>
                                        <th>Remarks</th>
                                    </tr>       
                                </thead>    
                            </table>
                            <button id="map-btn" type="button" class="btn btn-primary pull-right" data-toggle='modal' data-target='#map-modal'>
                                Map PA to IGA
                            </button>
                        </div>

                        <div class="box-footer">
                            <button type="button" class="btn btn-default pull-right">Cancel</button>
                            <button type="submit" class="btn btn-success pull-right">Send for Approval</button>
                            <button id="save-btn" type="button" class="btn btn-primary pull-right">Save</button>
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
        <script src="/OBESystem/resources/plugins/jQuery/jquery-2.2.3.min.js"></script>
        <!-- Bootstrap 3.3.6 -->
        <script src="/OBESystem/resources/bootstrap/js/bootstrap.min.js"></script>
        <!-- DataTables -->
        <script src="/OBESystem/resources/plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="/OBESystem/resources/plugins/datatables/dataTables.bootstrap.min.js"></script>
        <script src="/OBESystem/resources/plugins/datatables/datatable/dataTables.select.min.js"></script>
        <!-- Select2 -->
        <script src="/OBESystem/resources/plugins/select2/select2.full.min.js"></script>
        <!-- InputMask -->
        <script src="/OBESystem/resources/plugins/input-mask/jquery.inputmask.js"></script>
        <script src="/OBESystem/resources/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
        <script src="/OBESystem//resources/plugins/input-mask/jquery.inputmask.extensions.js"></script>
        <!-- date-range-picker -->
        <!--        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
                <script src="/OBESystem/resources/plugins/daterangepicker/daterangepicker.js"></script>-->
        <!-- bootstrap datepicker -->
        <script src="/OBESystem/resources/plugins/datepicker/bootstrap-datepicker.js"></script>
        <!-- bootstrap color picker -->
        <script src="/OBESystem/resources/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
        <!-- bootstrap time picker -->
        <script src="/OBESystem/resources/plugins/timepicker/bootstrap-timepicker.min.js"></script>
        <!-- SlimScroll 1.3.0 -->
        <script src="/OBESystem/resources/plugins/slimScroll/jquery.slimscroll.min.js"></script>
        <!-- iCheck 1.0.1 -->
        <script src="/OBESystem/resources/plugins/iCheck/icheck.min.js"></script>
        <!-- FastClick -->
        <script src="/OBESystem/resources/plugins/fastclick/fastclick.js"></script>
        <!-- AdminLTE App -->
        <script src="/OBESystem/resources/dist/js/app.min.js"></script>
        <!-- AdminLTE for demo purposes -->
        <script src="/OBESystem/resources/dist/js/demo.js"></script>
        <!-- self made-->
        <script src="/OBESystem/js/store_program_search.js"></script>
        <script src="/OBESystem/js/view_approver_list.js"></script>
        <script src="/OBESystem/js/map_PA_IGA.js"></script>
        <!-- Page script -->
        <script>
            $(function() {
                //Initialize Select2 Elements
                $(".select2").select2();

                //Datemask dd/mm/yyyy
                $("#datemask").inputmask("dd/mm/yyyy", {"placeholder": "dd/mm/yyyy"});
                //Datemask2 mm/dd/yyyy
                $("#datemask2").inputmask("mm/dd/yyyy", {"placeholder": "mm/dd/yyyy"});
                //Money Euro
                $("[data-mask]").inputmask();

                //Date range picker
                // $('#reservation').daterangepicker();
                //Date range picker with time picker
                //    $('#reservationtime').daterangepicker({timePicker: true, timePickerIncrement: 30, format: 'MM/DD/YYYY h:mm A'});
                //Date range as a button
//                $('#daterange-btn').daterangepicker(
//                        {
//                            ranges: {
//                                'Today': [moment(), moment()],
//                                'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
//                                'Last 7 Days': [moment().subtract(6, 'days'), moment()],
//                                'Last 30 Days': [moment().subtract(29, 'days'), moment()],
//                                'This Month': [moment().startOf('month'), moment().endOf('month')],
//                                'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
//                            },
//                            startDate: moment().subtract(29, 'days'),
//                            endDate: moment()
//                        },
//                function(start, end) {
//                    $('#daterange-btn span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
//                }
//                );

                //Date picker
//                $('#datepicker').datepicker({
//                    autoclose: true
//                });

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
