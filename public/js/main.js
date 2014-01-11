$(document).ready(function() {

    $('ul:first')
        .attr({
            'class': 'ui large vertical styled sidebar menu'
        })
        .children().addClass('item')
    ;
    $('#main')
        .prepend($('#toc'))
    ;

})
