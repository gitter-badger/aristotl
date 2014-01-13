$(document).ready(function() {

    $('#index-jump input').keypress(function(e) {
        if ( e.which == 13 ) {
            var entry = $(this).val();
            var url   = '/entries/' + entry;
            window.open(url);
        }
    })

    $('#toc')
        .append($('ul:first'))
        .find('ul').addClass('small pointing menu')
        .find('li').addClass('item')
    ;

    $('#toc')
        .children('ul')
        .addClass('content')
    ;

    $('.ui.accordion')
        .accordion()
    ;

    $('.ui.accordion .title')
        .accordion('toggle')
    ;

    $('#toc')
        .find('item')
        .click(function() {
            $(this)
                .addClass('active')
            ;
        })
    ;

    $('ul:first')
        .children()
        .addClass('item')
    ;

    $('.ui.sidebar')
        .sidebar()
    ;

    $('.launch')
        .click(function(){
            $('.ui.sidebar').sidebar('toggle');
        })
    ;

})
