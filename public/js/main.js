function sidebarOverlay(boolean) {
    $('.sidebar')
        .sidebar({
            overlay: boolean
        })
    ;
}

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
        .accordion('open')
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
        .sidebar({
            overlay: true
        })
    ;

    $('.launch')
        .click(function(){
            $('.ui.sidebar').sidebar('toggle');
        })
    ;
    $('#open-menu')
        .mouseenter(function(){
            $(this)
                .stop()
                .animate({
                    width: '100px'
                }, 300, function() {
                    $(this).find('.text').show();
                })
                .addClass('black')
            ;
        })
        .mouseleave(function(event) {
            $(this).find('.text').hide();
            $(this)
                .stop()
                .animate({
                    width: '40px'
                }, 300)
                .removeClass('black')
            ;
        })
    ;

    if ($(window).width() > 1260)  {
        sidebarOverlay(false);
    } else {
        sidebarOverlay(true);
    }
    $(window).on('resize', function() {
        if ($(window).width() > 1260) {
            sidebarOverlay(false);
        } else {
            sidebarOverlay(true);
        }
    })
})
