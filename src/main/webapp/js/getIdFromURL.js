function getIdFromURL(query) {

    var url_string = query; //window.location.href
    var url = new URL(url_string);
    var id = url.searchParams.get("id");
    return id;
}