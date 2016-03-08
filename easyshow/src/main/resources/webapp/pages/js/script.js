/*  Table of Contents 
01. MENU ACTIVATION
02. GALLERY JAVASCRIPT
03. FITVIDES RESPONSIVE VIDEOS
04. MOBILE SELECT MENU
05. prettyPhoto Activation
06. Form Validation
07. Light Shortcodes
08. Backstretch
09. SCROLLTOFIXED
10. Isotope
11. ProgressionPlayer
*/


jQuery(document).ready(function($) {
	'use strict';


/*
=============================================== 01. MENU ACTIVATION  ===============================================
*/

	 jQuery('nav#site-navigation ul.sf-menu').superfish({
			 	popUpSelector: 'ul.sub-menu,.sf-mega', 	// within menu context
	 			delay:      	400,                	// one second delay on mouseout
	 			speed:      	200,               		// faster animation speed
		 		speedOut:    	200,             		// speed of the closing animation
				animation: 		{opacity: 'show'},		// animation out
				animationOut: 	{opacity: 'hide'},		// adnimation in
		 		cssArrows:     	true,              		// set to false
			 	autoArrows:  	true                    // disable generation of arrow mark-up
	 });
	 
	 
	 jQuery('#footer-menu-progression ul.sf-menu').superfish({
			 	popUpSelector: 'ul.sub-menu,.sf-mega', 	// within menu context
	 			delay:      	400,                	// one second delay on mouseout
	 			speed:      	200,               		// faster animation speed
		 		speedOut:    	200,             		// speed of the closing animation
				animation: 		{opacity: 'show'},		// animation out
				animationOut: 	{opacity: 'hide'},		// adnimation in
		 		cssArrows:     	true,              		// set to false
			 	autoArrows:  	true                    // disable generation of arrow mark-up
	 });	



/*
=============================================== 02. GALLERY JAVASCRIPT  ===============================================
*/

    $('.gallery-progression').flexslider({
		animation: "fade",      
		slideDirection: "horizontal", 
		slideshow: false,         
		slideshowSpeed: 7000,  
		animationSpeed: 250,        
		directionNav: true,             
		controlNav: true
    });
    
   $('#progression-home-slider .flexslider').flexslider({
		animation: "fade",      
		slideDirection: "horizontal", 
		slideshow: false,         
		slideshowSpeed: 7000,  
		animationSpeed: 500,        
		directionNav: true,
        keyboard: true,
		controlNav: true
    });    


/*
=============================================== 03. FITVIDES RESPONSIVE VIDEOS  ===============================================
*/

	$("#main").fitVids();

/*
=============================================== 04. MOBILE MENU  ===============================================
*/

  	$('ul.mobile-menu-progression').slimmenu({
  	    resizeWidth: '1200',
  	    collapserTitle: 'Menu',
  	    easingEffect:'easeInOutQuint',
  	    animSpeed:'medium',
  	    indentChildren: false,
  		childrenIndenter: '- '
  	});

    
	var clickOrTouch = (('ontouchend' in window)) ? 'touchend' : 'click';
	
	$(".mobile-menu-icon-progression").on(clickOrTouch, function(e) {
	   $(".site-header-progression").toggleClass("active-menu-icon-progression");
	});
    
    

/*
=============================================== 05. prettyPhoto Activation  ===============================================
*/

	jQuery("a[data-rel^='prettyPhoto']").prettyPhoto({
		animation_speed: 'fast', /* fast/slow/normal */
		slideshow: 5000, /* false OR interval time in ms */
		autoplay_slideshow: false, /* true/false */
		opacity: 0.80, /* Value between 0 and 1 */
		show_title: false, /* true/false */
		allow_resize: true, /* Resize the photos bigger than viewport. true/false */
		default_width: 500,
		default_height: 344,
		counter_separator_label: '/', /* The separator for the gallery counter 1 "of" 2 */
		theme: 'pp_default', /* light_rounded / dark_rounded / light_square / dark_square / facebook */
		horizontal_padding: 20, /* The padding on each side of the picture */
		hideflash: false, /* Hides all the flash object on a page, set to TRUE if flash appears over prettyPhoto */
		wmode: 'opaque', /* Set the flash wmode attribute */
		autoplay: false, /* Automatically start videos: True/False */
		modal: false, /* If set to true, only the close button will close the window */
		deeplinking: false, /* Allow prettyPhoto to update the url to enable deeplinking. */
		overlay_gallery: false, /* If set to true, a gallery will overlay the fullscreen image on mouse over */
		keyboard_shortcuts: true, /* Set to false if you open forms inside prettyPhoto */
		ie6_fallback: true,
		social_tools: '' /* html or false to disable  <div class="pp_social"><div class="twitter"><a href="http://twitter.com/share" class="twitter-share-button" data-count="none">Tweet</a><script type="text/javascript" src="http://platform.twitter.com/widgets.js"></script></div><div class="facebook"><iframe src="http://www.facebook.com/plugins/like.php?locale=en_US&href='+location.href+'&amp;layout=button_count&amp;show_faces=true&amp;width=500&amp;action=like&amp;font&amp;colorscheme=light&amp;height=23" scrolling="no" frameborder="0" style="border:none; overflow:hidden; width:500px; height:23px;" allowTransparency="true"></iframe></div></div> */
	});


/*
=============================================== 06. Form Validation  ===============================================
*/


	$("#CommentForm").validate();
		
	
/*
=============================================== 07. Light Shortcodes  ===============================================
*/
	
	
	// Accordion
	$(".ls-sc-accordion").accordion({heightStyle: "content"});
	
	// Toggle
	$(".ls-sc-toggle-trigger").click(function(){$(this).toggleClass("active").next().slideToggle("fast");return false; });
	
	// Tabs
	$( ".ls-sc-tabs" ).tabs();
	




/*
=============================================== 08. Backstretch ===============================================
*/	

	$("body.about #soundbyte-page-title, body.episodes #soundbyte-page-title, body.contact #soundbyte-page-title, body.blog #soundbyte-page-title, body.donate #soundbyte-page-title ").backstretch([ "images/demo/page-title.jpg" ],{ fade: 750, centeredY:true }); 
    $("body.single-episode #soundbyte-page-title").backstretch([ "images/demo/page-title-2.jpg" ],{ fade: 750, centeredY:true }); 
		
/*
=============================================== 09. SCROLLTOFIXED  ===============================================
*/

	if ($(this).width() > 959) {
    $('#sticky-header-progression').scrollToFixed();
	}
	
    var header = $(".menu-default-progression");
       $(window).scroll(function() {
		   
		   if ($(this).width() > 959) {
			   
	           var scroll = $(window).scrollTop();

	           if (scroll >= 2) {
	               header.removeClass('menu-default-pro').addClass("menu-resized-pro");
	           } else {
	               header.removeClass("menu-resized-pro").addClass('menu-default-pro');
	           }
		   
		   } 
		   
       });
	
/*
=============================================== 10. ISOTOPE  ===============================================
*/
	 	
			
	var $winsize = $(window).width();
    var $isotopewidth = $('.isotope').width();
	var $guttersize = $isotopewidth * 0.04;
    if ($( ".isotope" ).hasClass( "full-width-progression" )) {
        var $guttersize = 0;
    }
    
	
	var $isocontainer = $('.isotope');
	$('.isotope').imagesLoaded( function(){
		// init Isotope
		$('.isotope').isotope({ filter: '.init' });
		var $container = $('.isotope').isotope({
			itemSelector: '.isotope-item',
            layoutMode: 'masonry',
			masonry: { 
                gutter: $guttersize,
                columnWidth: '.isotope-item'
            },
			transitionDuration: '0.8s'
		});
	  // filter functions
	  var filterFns = {
	  };
	  // bind filter button click
	  $('#filters').on( 'click', 'button', function() {
		var filterValue = $( this ).attr('data-filter');
		// use filterFn if matches value
		filterValue = filterFns[ filterValue ] || filterValue;
		$container.isotope({ filter: filterValue });
	  });
	  // change is-checked class on buttons
	  $('.button-group').each( function( i, buttonGroup ) {
		var $buttonGroup = $( buttonGroup );
		$buttonGroup.on( 'click', 'button', function() {
		  $buttonGroup.find('.is-checked').removeClass('is-checked');
		  $( this ).addClass('is-checked');
		});
	  });
        
        
		/* Begin Infinite Scroll */
		var $isocontainer = $('.isotope');
        $isocontainer.infinitescroll({
          errorCallback: function(){  $('#infinite-nav-pro').delay(500).fadeOut(500, function(){ $(this).remove(); }); },
		  navSelector  : '#infinite-nav-pro',    // selector for the paged navigation 
		  nextSelector : '.nav-previous a',  // selector for the NEXT link (to page 2)
		  itemSelector : '.isotope-item',     // selector for all items you'll retrieve
	   		loading: {
	   		 	img: 'images/loader.gif',
	   			msgText: "",
	   		 	finishedMsg: "<div id='no-more-posts'>No more posts</div>",
	   		 	speed: 0, }
		  },
		  // trigger Masonry as a callback
		  function( newElements ) {
			  
		    var $newElems = $( newElements );

				$newElems.imagesLoaded(function(){
				    $container.isotope( 'appended', $newElems );

			});
		   
		  }
		);
		
		// Pause Infinite Scroll
		$(window).unbind('.infscr');
		// Resume Infinite Scroll
		$('.nav-previous a').click(function(){
			$container.infinitescroll('retrieve');
			return false;
		});
		/* End Infinite Scroll */
	
	        
        

	//Timeout
	setTimeout(function() {
	$isocontainer.isotope('layout');
	}, 120); 
	  
	});	 

	
	//Isotope Reorder Layout on Window Resize
	var tilefix;
	$(window).on('resize', function() {
		tilefix = $('.isotope-item').width();
		$isocontainer.isotope('layout');
	});
				
/*
=============================================== 11. ProgressionPlayer  ===============================================
*/	
	
//
//$('.progression-single').mediaelementplayer({
//	audioWidth: 400, // width of audio player
//	audioHeight:40, // height of audio player
//	startVolume: 0.5, // initial volume when the player starts
//	features: ['playpause','current','progress','duration','tracks','volume','fullscreen']
//	});
//
//$('.progression-playlist').mediaelementplayer({
//	audioWidth: 400, // width of audio player
//	audioHeight:40, // height of audio player
//	startVolume: 0.5, // initial volume when the player starts
//	loop: false, // useful for <audio> player loops
//	features: ['playlistfeature', 'prevtrack', 'playpause', 'nexttrack','current', 'progress', 'duration', 'volume', 'playlist'],
//	playlist: true, //Playlist Show
//	playlistposition: 'bottom' //Playlist Location
//	});

	
	
// END
});

$(document).on("click", ".toggle-scan", function() {
	$(this).toggleClass("active").next().slideToggle("fast");
	return false;
});