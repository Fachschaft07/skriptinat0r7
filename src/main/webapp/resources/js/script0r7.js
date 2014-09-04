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