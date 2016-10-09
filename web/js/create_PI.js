var inputs = $("#PI-inputs");
var dropDown = $("#select-PO");
var codePO = "";
var rowCount = 0;
var count = 0;
$(document).ready(function () {
    dropDown.change(function () {
        inputs.html("");
        //inputs.remove();
        rowCount = 0;
        count = 0;
        codePO = dropDown.val();
        console.log("newcodePO: " + codePO);
        getAllPI(codePO);
        getLastPI(codePO);
    });
});

function getAllPI(codePO) {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAllMapPItoPO?codePO=" + codePO,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            data.forEach(addRow);
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function getLastPI(codePO) {
    console.log("entered addRow codePO: " + codePO);
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetLastPI?SelectedPO=" + codePO,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            var lastCodeIGA = data;
            var dec = 0;
            console.log("lastCode: " + lastCodeIGA);
            if (lastCodeIGA) {
                count = parseFloat(lastCodeIGA.replace(/[^0-9\.]+/g, ''));
                dec = (count % 1).toFixed(2);
            }
            console.log("decimal: " + dec);
            $('#addRowButton').click(function () {
                var newCodeIGA;
                
                console.log("code PO: " + codePO);
                dec = (dec * 1000 + 0.01 * 1000) / 1000;
                var final = dec.toString();
                final = final.replace(/^0+/, '');
                if (dec === 0.1) {
                    newCodeIGA = codePO + "" + final + "0";
                } else {
                    newCodeIGA = codePO + "" + final;
                }
                console.log("newPI: " + newCodeIGA);
                console.log("rowCount: " + rowCount);

                var div = '<div id="inputs' + rowCount + '" >' +
                        '<span id = "codePI">' + newCodeIGA + '</span>' +
                        '<input class="hidden" name="codePI" value="' + newCodeIGA + '">' +
                        '<div class="input-group input-group-sm">' +
                        '<input name="description" type="text" class="form-control" placeholder="Add Performance Indicator" required>' +
                        '<span class="input-group-btn">' +
                        '<button type="button" class="btn bg-green btn-flat"><i class="fa fa-edit"></i></button>' +
                        '<button onClick="deleteRow(' + rowCount + ')" type="button" class="btn btn-danger btn-flat"><i class="fa fa-times"></i></button>' +
                        '</span>' +
                        '</div>' +
                        '<br>' +
                        '</div>';

                inputs.append(div);
                rowCount++;
            });
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function addRow(data) {
    var codePI = data.codePI;
    var description = data.description;

    console.log("rowCount: " + rowCount);

    var div = '<div id="inputs' + rowCount + '" >' +
            '<span id = "codePI">' + codePI + '</span>' +
            '<input class="hidden" name="codePI" value="' + codePI + '">' +
            '<div class="input-group input-group-sm">' +
            '<input name="description" type="text" class="form-control" value="' + description + '" readOnly>' +
            '<span class="input-group-btn">' +
            '<button type="button" class="btn bg-green btn-flat"><i class="fa fa-edit"></i></button>' +
            '<button onClick="deleteRow(' + rowCount + ')" type="button" class="btn btn-danger btn-flat"><i class="fa fa-times"></i></button>' +
            '</span>' +
            '</div>' +
            '<br>' +
            '</div>';
    inputs.append(div);
    rowCount++;
}

function deleteRow(num) {
    var retVal = confirm("Are you sure you want to delete this row?");
    if (retVal === true) {
        var tr = 'inputs' + num;
        document.getElementById(tr).remove();
        rowCount--;
        count--;
        return true;
    } else {
        console.log("cancelled");
        return false;
    }
}
