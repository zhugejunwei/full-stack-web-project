require([
        "dojo/query",
        "dojo/dom",
        "dojo/dom-geometry",
        "dojo/dom-class",
        "dojo/dom-style",
        "dojo/dom-construct",
        "dijit/focus",
        "dojo/parser",
        "dijit/form/DateTextBox",
        "dojo/NodeList-traverse",
        "dojo/domReady!"
    ], function(query, dom, domGeom, domClass, domStyle, domConstruct, focusUtil, parser){

        // Move the focused-field class to the field-group that contains the focused input.
        var handle = focusUtil.watch("curNode", function(name, oldValue, newValue){
            var parent = query(newValue).closest('.field-group');
            if(!domClass.contains(parent, "focused-field")) {
                query('.focused-field').removeClass('focused-field');
                parent.addClass('focused-field');
            }
        });

        // Add error class to parent wrapper and only show error borders on empty fields in addresses
        query('.feedback.error').closest('.field-group').addClass('error');
        query('.addressfield input:text[value=""]').addClass('empty');

        // Focus first input of birthdayfield or phonefield-us when parent wrapper is clicked
        query('div.subfields').on("click", function(e){
            var firstInput = query('input', e.target)[0];
            focusUtil.focus(firstInput);
        });

        // Prevent image blowout in IE6 since it doesn't support max-width
        if (domClass.contains(query('html')[0], "ie-lte7")){

            query('img').forEach(function(node){
                var computedStyle = domStyle.getComputedStyle(node);
                var w = domGeom.getContentBox(node, computedStyle).w;
                console.log(w);
                if(w >= 600){
                    domStyle.set(node, "width", "600px");
                }
            });
        }

        // Use native checkboxes and radio buttons outside the app
        dojo.query(".radio input, .checkbox input").removeAttr("data-dojo-type");

        // Render DateTextBoxes
        parser.parse();

        query('form').on("submit",function(e){
            var invalidDate = dojo.query('.dijitTextBoxError');
            if(invalidDate.length){
                query('.dijitDateTextBox ~ .feedback.error').forEach(domConstruct.destroy);
                invalidDate.forEach(function(node) {
                    domConstruct.place('<div class="feedback error"><div class="errorText">Please enter a valid date</div></div>', node, 'after');
                });
                e.preventDefault();
            }
        });

        twemoji.parse(document.body, { size: 16 });
    });
	
// <script type="text/javascript">
	// Still including jQuery to support http://downloads.mailchimp.com/js/jquery.mailcheck.min.js which is included in forms.
google.load("jquery", "1.7.1");
	
// <script type="text/javascript">
$(document).ready(function(){
	        try {

	        var domains = [
	            'aim.com','aol.com','att.net','bellsouth.net','btinternet.com',
	            'charter.net','comcast.com', 'comcast.net','cox.net','earthlink.net',
	            'gmail.com','googlemail.com','icloud.com','mac.com','me.com','msn.com',
	            'optonline.net','optusnet.com.au', 'rocketmail.com','rogers.com','sbcglobal.net',
	            'shaw.ca','sympatico.ca','telus.net','verizon.net','ymail.com'];

	        var secondLevelDomains = ['yahoo', 'hotmail', 'mail', 'live', 'outlook', 'gmx'];

	        var topLevelDomains = ['com', 'com.au', 'com.tw', 'ca', 'co', 'co.nz', 'co.uk', 'de',
	            'fl', 'fr', 'it', 'ru', 'net', 'org', 'edu', 'gov', 'jp', 'nl', 'kr', 'se', 'eu',
	            'ie', 'co.il', 'us', 'at', 'be', 'dk', 'hk', 'es', 'gr', 'ch', 'no', 'cz',
	            'in', 'net', 'net.au', 'info', 'biz', 'mil', 'co.jp', 'sg', 'hu'];

	        $('#MERGE0').on('blur', function() {
	            var submitBtn = $('input.button:submit')[0];
	            if(submitBtn && submitBtn.value == "Subscribe to list"){ // Only show mailcheck warning on english forms.
	                $(this).mailcheck({
	                    domains: domains,
	                    secondLevelDomains: secondLevelDomains,
	                    topLevelDomains: topLevelDomains,
	                    suggested: function(element, suggestion) {
	                        var msg = 'Hmm, did you mean '+suggestion.full+'? If not, carry on.';
	                        if ( $('#MERGE0_mailcheck').length == 0 ){
	                            element.after('<div id="MERGE0_mailcheck" class="feedback">'+msg+'</div>');
	                            element.closest('.field-group').addClass('mailcheck');
	                        }
	                        var enteredDomain = element[0].value.trim().split('@')[1];
	                        ga('send', 'event', 'mailcheck', enteredDomain, suggestion.domain);
	                    },
	                    empty: function(element) {
	                        if ( $('#MERGE0_mailcheck').length > 0 ){
	                            $('#MERGE0_mailcheck').remove();
	                            element.closest('.field-group').removeClass('mailcheck');
	                        }
	                        return;
	                    }
	                });
	            }
	        });
	        } catch(e){ console.log(e); }
});

// last 1
var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
// last 2
try {
var _gaq = _gaq || [];
	_gaq.push(["_setAccount", "UA-329148-88"]);
	_gaq.push(["_setDomainName", ".list-manage.com"]);
	_gaq.push(["_trackPageview"]);
	_gaq.push(["_setAllowLinker", true]);
} catch(err) {console.log(err);}