// ajax navigation bar search
(function() {
    var suggestions = new Bloodhound({
        datumTokenizer: Bloodhound.tokenizers.obj.whitespace('value'),
        queryTokenizer: Bloodhound.tokenizers.whitespace,
        remote: '/skripte/search?q=%QUERY',
    });
    
    suggestions.initialize();
    
    $('#search').typeahead(null, {
        name: 'search',
        displayKey: 'name',
        highlight: true,
        source: suggestions.ttAdapter(),
        templates: {
            empty: '<div class="tt-empty">Keine Ergebnisse gefunden</div>'
        }
    });
})();

// clickable table rows
(function() {
    jQuery("[data-href]").on("click", function() {
        window.location = $(this).data("href");
    });
})();

// data-confirm tag
(function() {
    jQuery("[data-confirm]").on("click", function() {
        return confirm("Sind Sie sicher?");
    });
})();

// generic check all selector
(function() {
    jQuery(".check-all").on("change", function() {
        var target = jQuery(this).data("target");
        var state = jQuery(this).prop("checked");
        jQuery(target).prop("checked", state);
    });
    
    jQuery(document).ready(function() {
        // Set initial states
        jQuery(".check-all").each(function() {
            jQuery(this).change();
        });
    });
})();

//################################################
//          VIEW SPECIFIC HANDLERS
//################################################

// scripts/submit-summarize
(function() {
    $("#sort-scripts").sortable({
        placeholder: "",
    });
})();
