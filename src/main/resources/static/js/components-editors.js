var ComponentsEditors = function () {
    
    var handleWysihtml5 = function () {
        if (!jQuery().wysihtml5) {
            return;
        }

        if ($('.wysihtml5').size() > 0) {
            $('.wysihtml5').wysihtml5({
                "stylesheets": ["../assets/global/plugins/bootstrap-wysihtml5/wysiwyg-color.css"]
            });
        }
    }

    var handleSummernote = function () {
        $('#summernote_1').summernote({height: 500});
        //API:
        //var sHTML = $('#summernote_1').code(); // get code
        //$('#summernote_1').destroy(); // destroy
        var summernoteForm = $('textarea[name="summernote"]').html($('#summernote_1').code());
    }
    
    var handleSummernoteNewsAdd = function () {
        $('#summernote_2').summernote({height: 500});
        //API:
        //var sHTML = $('#summernote_1').code(); // get code
        //$('#summernote_1').destroy(); // destroy
        var newsAddForm = $('textarea[name="summernote"]').html($('#summernote_2').code());
    }
    


    return {
        //main function to initiate the module
        init: function () {
            handleWysihtml5();
            handleSummernote();
            handleSummernoteNewsAdd();
        }
    };

}();

jQuery(document).ready(function() {    
   ComponentsEditors.init(); 
});