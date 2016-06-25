// Replace words or phrases.
var elements = document.getElementsByTagName('*');

for (var i = 0; i < elements.length; i++) {
    var element = elements[i];

    for (var j = 0; j < element.childNodes.length; j++) {
        var node = element.childNodes[j];

        if (node.nodeType === 3) {
            var text = node.nodeValue;			
			
			// PLANETS
			getFullName(element,text,'ACPL','Säurenplanet');
			getFullName(element,text,'ASFI','Asteroidenfeld');
			getFullName(element,text,'CLPL','Wolkenfeld');
			getFullName(element,text,'CRPL','Kristallplanet');
			getFullName(element,text,'DSPL','Wüstenplanet');
			getFullName(element,text,'EOPL','Ethanozeanplanet');
			getFullName(element,text,'FOPL','Waldplanet');
			getFullName(element,text,'HYPL','Wasserstoffplanet');
			getFullName(element,text,'ICPL','Eisplanet');
			getFullName(element,text,'IDPL','Eisenerzplanet');
			getFullName(element,text,'IMOS','Imperiales Mutterschiff');
			getFullName(element,text,'JUPL','Dschungelplanet');
			getFullName(element,text,'LMOS','Großes Mutterschiff');
			getFullName(element,text,'LVPL','Lavaplanet');
			getFullName(element,text,'MIPL','Mineralinselplanet');
			getFullName(element,text,'MUPL','Staubplanet');
			getFullName(element,text,'RKPL','Felsenplanet');
			getFullName(element,text,'SMOS','Kleines Mutterschiff');
			getFullName(element,text,'STPL','Standardplanet');
			getFullName(element,text,'VUPL','Vulkanplanet');		
			getFullName(element,text,'WAPL','Wasserplanet');
			
			// PROBES
			getFullName(element,text,'SEDR','Kleine Foschungssonde');
			getFullName(element,text,'SSCD','Kleine Kundschaftersonde');
			getFullName(element,text,'SSDR','Kleine Spionagesonde');
			getFullName(element,text,'EXDR','Forschungssonde');
			getFullName(element,text,'SPDR','Spionagesonde');
			getFullName(element,text,'SCDR','Kundschaftersonde');
			getFullName(element,text,'SUDR','Überwachungssonde');
			getFullName(element,text,'SPND','Spionagenanosonde');
			getFullName(element,text,'PRDR','Prospektierungssonde');
			getFullName(element,text,'LPRD','Langstrecken Prospektierungssonde');
			getFullName(element,text,'SPST','Spionagesatellit');
			getFullName(element,text,'ASPD','Spionagesonde der Sciweens');
			getFullName(element,text,'ASCD','Kundschaftersonde der Sciweens');
			getFullName(element,text,'ASED','Forschungssonde der Sciweens');
			getFullName(element,text,'RSCD','Arkane Kundschaftersonde');
			getFullName(element,text,'ASUD','Überwachungssonde der Nux');
			
			// COLOSHIPS		
			getFullName(element,text,'SCOL','Kleines Kolonisationsschiff');
			getFullName(element,text,'GRCO','Generationen-Kolonisationsschiff');
			getFullName(element,text,'CCOL','Kolonisationsschiff');
			getFullName(element,text,'FCOL','Schnelles Kolonisationsschiff');
			getFullName(element,text,'LRCO','Langstrecken Kolonisationsschiff');
			getFullName(element,text,'ICOL','Imperiales Kolonisationsschiff');
			getFullName(element,text,'GCOL','Kolonisationsschiff der Gilde');
			getFullName(element,text,'ASCS','Asteroidenkontrollschiff');
			getFullName(element,text,'LACS','Großes Asteroidenkontrollschiff');
			getFullName(element,text,'IACS','Imperiales Asteroidenkontrollschiff');
			
			// Jagdschiff
			getFullName(element,text,'WADR','Kampfdrohne');
			getFullName(element,text,'LFGT','Leichter Jäger');
			getFullName(element,text,'SFGT','Tarnkappen Jäger');
			getFullName(element,text,'HFGT','Schwerer Jäger');
			getFullName(element,text,'PFGT','Plasma Jäger');
			getFullName(element,text,'AFGT','Jäger der Keelaak');
			getFullName(element,text,'AIDC',"Aufblasbares Köderschiff der Kee'laak");
			getFullName(element,text,'RKDR','Arkane Kamikazedrohne');
			
			// Kampfschiff
			getFullName(element,text,'CORV','Korvette');
			getFullName(element,text,'MSFR','Raketenfregatte');
			getFullName(element,text,'DSTR','Zerstörer');
			getFullName(element,text,'ACRS','Panzerkreuzer');
			getFullName(element,text,'DNGT','Schlachtschiff');
			getFullName(element,text,'BTST','Kampfstern');
			getFullName(element,text,'DTST','Todesstern');
			getFullName(element,text,'ABAT','Schlachtschiff der Keelaak');
			getFullName(element,text,'RBCR','Arkaner Schlachtkreuzer');
			getFullName(element,text,'GDST','Gildenzerstörer');
			
			// Bomber
			getFullName(element,text,'LBOM','Leichter Bomber');
			getFullName(element,text,'SBOM','Tarnkappen Bomber');
			getFullName(element,text,'HBOM','Schwerer Bomber');
			getFullName(element,text,'ABOM','Armageddon Bomber');
			getFullName(element,text,'ALBO','Bomber der Keelaak');
			getFullName(element,text,'ACRM','Marschflugkörper der Keelaak');
			getFullName(element,text,'RLBO','Arkaner Langstreckenbomber');
			
			// Flaggschiff
			getFullName(element,text,'PFLS','Interplanetares Flaggschiff');
			getFullName(element,text,'SFLS','Kleines Flaggschiff');
			getFullName(element,text,'QFLS','Staffel Flaggschiff');
			getFullName(element,text,'WFLS','Geschwader Flaggschiff');
			getFullName(element,text,'BFLS','Brigade Flaggschiff');
			getFullName(element,text,'IFLS','Imperiales Flaggschiff');
			getFullName(element,text,'AFLS','Flaggschiff der Quipgrex');
			getFullName(element,text,'RFLS','Arkanes Flaggschiff');		

			// Fabrikschiff
			getFullName(element,text,'PRCY','Interplanetarer Recycler');
			getFullName(element,text,'PMIV','Interplanetares Bergbauschiff');
			getFullName(element,text,'RCYC','Recycler');
			getFullName(element,text,'MIVE','Bergbauschiff');
			getFullName(element,text,'RFVE','Raffinerieschiff');
			getFullName(element,text,'CNSV','Konstruktionsschiff');
			getFullName(element,text,'ARCY','Recycler der Diggren');
			getFullName(element,text,'AMIV','Bergbauschiff der Diggren');
			getFullName(element,text,'RRFV','Arkanes Raffinerieschiff');
			
			// Spezialschiff
			getFullName(element,text,'SMSW','Kleiner Minenräumer');
			getFullName(element,text,'LMSW','Großer Minenräumer');
			getFullName(element,text,'EXCW','Ausgrabungsplattform');
			getFullName(element,text,'FLDP','Flottenmaskierungsschiff');
			getFullName(element,text,'HGBC','Sprungtor Feuerschiff');
			getFullName(element,text,'SBTR','Kleiner Gebäudetransporter');
			getFullName(element,text,'LBTR','Großer Gebäudetransporter');
			getFullName(element,text,'IBTR','Imperialer Gebäudetransporter');
			getFullName(element,text,'ASPM','Geologieschiff der Diggren');
			getFullName(element,text,'ACIP','Planeteneinschlagsdrohnen der Diggren');
			
			// Transportschiff
			getFullName(element,text,'PTRN','Interplanetarer Transporter');
			getFullName(element,text,'STRN','Kleiner Transporter');
			getFullName(element,text,'LTRN','Großer Transporter');
			getFullName(element,text,'HTRN','Schnelltransporter');
			getFullName(element,text,'GTRN','Gildentransporter');
			getFullName(element,text,'ITRN','Imperialer Transporter');
			getFullName(element,text,'FROR','Fracht Orbiter');
			getFullName(element,text,'ATRN','Transporter der Quipgrex');
			getFullName(element,text,'RTRN','Arkaner Transporter');
			
			// Tankschiffe
			getFullName(element,text,'PTNK','Interplanetares Tankschiff');
			getFullName(element,text,'STNK','Kleiner Tanker');
			getFullName(element,text,'LTNK','Großer Tanker');
			getFullName(element,text,'HTNK','Schnelltanker');
			getFullName(element,text,'ITNK','Imperiales Tankschiff');
			getFullName(element,text,'ATNK','Tanker der Quipgrex');
			
			// Trägerschiffe
			getFullName(element,text,'SCAR','Geschwader Trägerschiff');
			getFullName(element,text,'FCAR','Flottenträgerschiff');
			getFullName(element,text,'ACAR','Armadaträgerschiff');
			getFullName(element,text,'ICAR','Imperiales Trägerschiff');
			
			// Truppentransporter
			getFullName(element,text,'PLDC','Interplanetares Landungsboot');
			getFullName(element,text,'STRT','Kleiner Truppentransporter');
			getFullName(element,text,'LTRT','Großer Truppentransporter');
			getFullName(element,text,'HTRT','Schneller Truppentransporter');
			getFullName(element,text,'ITRT','Imperialer Truppentransporter');
				
//            var replacedText = text.replace(/Dauer/gi, 'TEST');

//            if (replacedText !== text) {
//				var newNode = document.createTextNode(replacedText);
//				element.setAttribute('title','Ein Test');
				//newNode.setAttribute('title',"Ein Test");
//				element.replaceChild(newNode, node);
                //element.replaceChild(document.createTextNode(replacedText), node);
            //}
        }
    }
}

// Listen for messages
chrome.runtime.onMessage.addListener(function (msg, sender, sendResponse) {
    // If the received message has the expected format...
    if (msg.text === 'report_back') {
        // Call the specified callback, passing
        // the web-page's DOM content as argument
		// Look wheather it is a highscore list
		if(window.frames["lh_stat_main"]){
			sendResponse(window.frames["lh_stat_main"].document.all[0].outerHTML);	
		}else{
			sendResponse(document.all[0].outerHTML);	
		}
    }
});

function getFullName(element,text,abbreviation,fullname){
	if (text.indexOf(abbreviation) > -1){
		var tooltip = abbreviation + ": " + fullname;
		element.setAttribute('title',tooltip)
	}
}