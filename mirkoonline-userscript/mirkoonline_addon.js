$('#nav > div > ul.clearfix.mainnav > li:nth-child(5) > a').html($('#nav > div > ul.clearfix.mainnav > li:nth-child(5) > a').text() + ' <em class="mark-number mirko-counter">...</em>');
$.ajax({
        url: 'http://mirkoonline.herokuapp.com/jsonp',
        dataType: 'jsonp',
        success: function(data){
                console.log(data);
                $('.mirko-counter').text('Aktywni: '+data.counter);
        },
        error: function() {
                $('.mirko-counter').text('?');
        }
});