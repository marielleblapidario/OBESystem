var igaDropdown = $('.select-iga');
var programCode = sessionStorage.getItem("programCode");
var table = $('#data');

$(document).ready(function(){
    getAllPA(programCode);
    
    
});

function getAllPA(program) {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAllPA?SelectedProgram=" + program,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            data.forEach(addRow);
            getAllIGA();
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function getAllIGA() {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAllIGA",
        dataType: 'json',
        success: function (data) {
            console.log(data);
            data.forEach(addOption);
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function addRow(data) {
    var codePA = data.codePA;

    table.append(
            '<tr>' +
            '<td>' + codePA + '</td>' +
            '<input type="hidden" name="codePA" class="readonlyWhite" id="codeIGA" value="' + codePA + '" />' +
            '<td>' +
            '<div class="form-group">' +
            '<select class="form-control select2 select2-hidden-accessible select-iga" multiple="" data-placeholder="Select IGA" style="width: 50%;" tabindex="-1" aria-hidden="true">' +
            '<option value="1">IGA01</option>'+
            '<option value="2">IGA02</option>'+
            '<option value="3">IGA03</option>'+
            '</select>' +
            '</div>' +
            '</td>' +
            '<td>' +
            '<span class="label label-success">pending</span>' +
            '<input type="hidden" name="status" class="readonlyWhite" id="codeIGA" value="pending" />' +
            '</td>' +
            '<td>' +
            '<div class="col-sm-10">' +
            '<input type="text" name="remarks" class="form-control no-border" id="remarks">' +
            '</div>' +
            '</td>' +
            '</tr>'
            );
}

function addOption(data) {
    var s = "<option value = " + data.codeIGA + ">" + data.description + "</option>";
    igaDropdown.append(s);

}