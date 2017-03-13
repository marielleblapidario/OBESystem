var example1 = $("#example1").DataTable({"order": []});

$(document).ready(function(){
     $.ajax({
            type: "GET",
            url: "/OBESystem/GetAllLog",
            dataType: 'json',
            success: function (data) {
                console.log(data);
                data.forEach(addRow);
            },
            error: function (response) {
                console.log(response);
            }
        });
});

function addRow(data){
    if(data.bfr_value == null || data.bfr_value == ''){
        data.bfr_value = " ";
    }
    if(data.ftr_value == null || data.ftr_value == ''){
        data.ftr_value = " ";
    }
    example1.row.add([
        data.log_no, 
        data.txn_no,  
        data.userName, 
        data.txn_time, 
        data.txn_type, 
        data.tbl_name,
        data.fld_name,
        data.bfr_value,
        data.ftr_value
    ]);
    example1.draw();
}