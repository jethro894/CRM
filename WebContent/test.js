/**
 * Created by dongxueliu on 10/22/14.
 */
//#1


var cloudMove = function(){
    var startPoint = 0.7;// start when contact page possess 30% of the window height
    var intervalID=[];
    var moveDuration = 20000;
    var originalWidth = $('.clouds').width();
    this.getEndPoint = function(){
        return ($('.clouds').width() * 0.9 - $('.cloud').width());
    };
    this.getNewWidth = function(){
        return ($('.clouds').width());
    }
    this.getPageHeight = function(){
        return $(window).innerHeight() * startPoint;
    };
    this.stop = function(goBacktoLeft){
        // clearInterval when user is not on the contact page
        // stop the current animtion, go back to the end. otherwise I don't know why
        // the top property always gets lower-- maybe because the callback function is the current animation
        // so its previous point is +10px after the first animation. but end point is the very origin point of cloud top
        //$('.cloud').stop(true, true);// stop at end point
        goBacktoLeft = typeof goBacktoLeft !== 'undefined'? goBacktoLeft : true;// whether goBaktoLeft is defined or not.
        clearInterval(intervalID[0]);
        clearInterval(intervalID[1]);
        $('.cloud').stop();
        if(goBacktoLeft){
            $('.cloud').css('left','0');
        }
        else{
            $('.cloud').css({'left':this.getEndPoint()});
        }
    };
    this.sliding = function(){
        $('.cloud').stop(true, true);
        var pageHight = this.getPageHeight();
        var vertical = $('#Contact').offset().top;
        var ratio =  Math.abs(1 - vertical / pageHight);
        var moveDistance = $('.clouds').width() * 0.9;
        var left = moveDistance * ratio;
        var cloudWidth = $('.cloud').width();
        var endPoint = moveDistance - cloudWidth;
        if (left <= endPoint){
            $('.cloud').css('left', left + 'px');
        }
    };
    this.float = function(){
        var endPoint = this.getEndPoint();
        // if use setTimeout(), then there is a waiting time at the first time it runs
        // can use immediately execute function or
        // can call back current function when animation is done, like the day I do it here
        // setInterval will execute the function again and again, setTimeout only execute once
        upDown( $('.cloud') );
        forwardBack( $('.cloud'), endPoint);
        intervalID[0] = setInterval(function(){
            upDown( $('.cloud'));
        },2000);
        intervalID[1] = setInterval(function(){
            forwardBack( $('.cloud'), endPoint);
        }, moveDuration * 2 );
    }
    var forwardBack = function(obj, endPoint){
        // cloud move back and force
        // queue and dequeue to let 'backForce' and 'updown' work sync
        obj.animate({
                'left': 0
            },
            {
                queue:'horizontal',
                duration:moveDuration,
                easing:'linear',
                complete:function(){
                    $(this).animate({
                            'left':endPoint
                        },
                        {
                            queue:'horizontal',
                            duration:moveDuration,
                            easing:'linear'
                        });
                }
            }).dequeue('horizontal');
    };
    var upDown = function(obj){
        // cloud move up and down
        obj.animate({
                'top': '50%'
            },
            {
                queue:'vertical',
                duration:1000,
                complete:function(){
                    $(this).animate({
                            'top': '60%'
                        },
                        {	queue:'vertical',
                            duration:1000
                        });
                }
            }).dequeue('vertical');
    };
    // when scroll to the contact page for the firstTime
    // has the slide effect
    //$('.cloud').stop();


    // the first time scroll to contact page, the cloud effect begins
    // if go back to this page, the effect is on, but it never stops
};



var loading = function(){
    $('.mailRotate').animate({
        'left':'20%',
        'top':'70%'
    }, {
        queue:false,
        duration:800,
        easing:'linear',
        complete:function(){
            $(this).animate({
                'left':'50%',
                'top':'5%',
                'opacity':'0'
            },{
                duration:800,
                easing:'linear',
                complete:function(){
                    $(this).css({
                        'left':'0',
                        'top':'0',
                        'opacity':'1'
                    });
                    loading();
                }

            });
        }
    });
};



