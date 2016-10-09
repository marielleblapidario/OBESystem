var programCode = sessionStorage.getItem("programCode");
var table = $('#map-table').DataTable({
    "processing": false,
    "paging": false,
    "lengthChange": false,
    "ordering": false,
    "info": true,
    select: {
        style: 'multi'
    }
});
var selectedPA = [];
var selectedPACodes = [];
var mapPAtoIGA = [];
var igaCheckboxDIV = $("#checkbox-div");

$(document).ready(function() {
    getAllIGA();
    getAllPA(programCode);

    /*
     * hides the "Map to PA to IGA" button 
     */
    $("#map-btn").hide();

    /*
     * when a row from the table is selected
     */
    $('#map-table tbody').on('click', 'tr', function() {
        selectedPA = [];
        selectedPACodes = [];

        /*
         * loops all selected rows
         */
        table.rows('.selected').every(function(rowIdx) {

            /*
             * gets the code from the first column of the selected row
             */
            codePA = table.row(rowIdx).data()[0];

            /*
             * saves it to the arraylist
             */
            selectedPACodes.push(codePA);

            /*
             * 'selectPA' is an object that contains the code and rowCount of 
             * the selected row
             */
            var selectPA = {code: codePA, ID: rowIdx};

            /*
             * saves it to arraylist
             */
            selectedPA.push(selectPA);
        });
        /*
         * checks if the map button should be shown or not
         */
        if (selectedPA.length > 0) {
            $("#map-btn").show();
        } else {
            $("#map-btn").hide();
        }
    });
    /*
     * when the "Map PA to IGA" is selected, the selected PAs will be shown in 
     * the modal
     */
    $("#map-btn").click(function() {
        $("#selected-PAs").text(selectedPACodes);
    });

    /*
     * when the "CONFIRM" button in the modal is clicked
     */
    $('#modal-confirm-btn').click(function() {
        var selectedIGA = [];

        /*
         * elements is an arraylist that contains all the checkboxes
         */
        var elements = document.getElementsByClassName('checkbox-option');
        for (var i = 0; elements[i]; i++) {

            /*
             * if the checkbox is checked, its value will be saved to the 
             * arraylist
             */
            if (elements[i].checked) {
                selectedIGA.push(elements[i].value);
            }
        }

        /*
         * loops through all the selected PAs
         */
        for (var x = 0; selectedPA[x]; x++) {

            /*
             * loops through all the selected IGAs
             */
            for (var j = 0; selectedIGA[j]; j++) {

                /*
                 * 'map' is an object that contains the PA Code and its assigned 
                 * IGA
                 */
                var map = {PA: selectedPA[x].code, IGA: selectedIGA[j]};
                /*
                 * object 'map' will be saved to the arraylist
                 */
                mapPAtoIGA.push(map);
                /*
                 * selectedPA[x].ID = the rowCount
                 * 1 = the column number
                 * 
                 * updates the value of the row under the first column from 
                 * "No assigned IGA" to the newly assigne IGA(s)
                 */
                table.cell(selectedPA[x].ID, 1).data(selectedIGA);
            }
        }

        /*
         * deselects all selected checkboxed from the modal        
         */
        $(".checkbox-option").prop('checked', false);
    });

    /*
     * when the 'Save' button is clicked
     */
    $("#save-btn").click(function() {

        /*
         * loops through all combinations of PA and IGA
         */
        for (var i = 0; mapPAtoIGA[i]; i++) {
            var PA = mapPAtoIGA[i].PA;
            var IGA = mapPAtoIGA[i].IGA;

            var Remarks = $("#" + PA).val();
            /*
             * PA = PA code
             * IGA = IGA code
             * 
             * sends PA and IGA as parameters
             * change URL to respective servlet URL
             */
            $.ajax({
                type: "POST",
                url: "/OBESystem/SampleMapPAtoIGA?SelectedPA=" + PA +
                        "&SelectedIGA=" + IGA + "&Remarks=" + Remarks,
                dataType: 'json',
                success: function() {
                },
                error: function(response) {
                    console.log(response);
                }});
        }
    });
});

function getAllPA(program) {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAllPA?SelectedProgram=" + program,
        dataType: 'json',
        success: function(data) {
            console.log(data);
            data.forEach(addRow);
        },
        error: function(response) {
            console.log(response);
        }
    });
}

function addRow(data) {
    var PA = data.codePA;
    var IGA = "No assigned IGA";
    var Status = "<span class=\"label label-success\">pending</span>";
    var Remarks = "<div class='col-sm-10'><input type='text' name='remarks' \n\
    class='form-control no-border' id=" + PA + "></div>";
    
    table.row.add([PA, IGA, Status, Remarks]);
    table.draw();
}

function getAllIGA() {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAllIGA",
        dataType: 'json',
        success: function(data) {
            console.log(data);
            data.forEach(addOption);
        },
        error: function(response) {
            console.log(response);
        }});
}

function addOption(data) {
    igaCheckboxDIV.append("<input type='checkbox' value='" + data.codeIGA 
            + "' class='checkbox-option' name='PO-BSINSYS-01'> " + data.codeIGA + "<br>");
}