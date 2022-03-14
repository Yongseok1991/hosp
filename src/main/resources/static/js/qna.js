$(function() {
    getQna(0);
})

let getQna = async (page) => {
    let response = await fetch(`/api/qna?page=${page}`);
    let responsePasing = await response.json();
    setQna(responsePasing);
}

let setQna = (responsePasing) => {
    console.log(responsePasing);

    let tbodyDom = document.querySelector("#qnaDom");
    tbodyDom.innerHTML = '';
    let pagenation = $("#pagination");
    pagenation.empty();
    responsePasing.content.forEach((e) => {
        let trEL = document.createElement("tr");
        let tdEL1 = document.createElement("td");
        let tdEL2 = document.createElement("td");
        let tdEL3 = document.createElement("td");
        let tdEL4 = document.createElement("td");

        tdEL1.innerHTML = e.id;
        tdEL2.innerHTML = e.title;
        tdEL3.innerHTML = e.writer == null ? '용돌' : e.writer;
        tdEL4 = e.createDate.substring(0, 16).replace('T', ' ')
        tdEL2.onclick = () => location.href=`/qna/${e.id}`;

        tdEL2.style.cursor= "pointer"

        trEL.append(tdEL1);
        trEL.append(tdEL2);
        trEL.append(tdEL3);
        trEL.append(tdEL4);
        tbodyDom.append(trEL);
    })

    let pageNumber = responsePasing.pageable.pageNumber;
    let pageSize = responsePasing.pageable.pageSize;
    let totalPages = responsePasing.totalPages;
    let startPage = Math.floor(pageNumber/pageSize) * pageSize +1;
    let endPage = startPage + pageSize -1;
    endPage = totalPages < endPage ? totalPages : endPage;

    let li0 = `<li class="page-item"><a class="page-link" href="javascript:void(0);" onclick="getQna(0);">Start</a></li>`;
    pagenation.append(li0);

    if(responsePasing.first == false) {
        let li1 = `<li class="page-item"><a class="page-link" href="javascript:void(0);" onclick="getQna('${startPage -11 > 0 ? startPage -11 : 0}');">previous</a></li>`;
        pagenation.append(li1);
    }
    for(let i= startPage; i<=endPage; i++) {
        if(pageNumber+1 == i) {
            let li2 = `<li class="page-item active"><a class="page-link" href="javascript:void(0);" onclick="getQna('${i - 1}');">${i}</a></li>`;
            pagenation.append(li2);
        } else {
            let li3 = `<li class="page-item"><a class="page-link" href="javascript:void(0);" onclick="getQna('${i - 1}');">${i}</a></li>`;
            pagenation.append(li3);
        }
    }

    if(responsePasing.last == false) {
        let li4 = `<li class="page-item"><a class="page-link" href="javascript:void(0);" onclick="getQna('${startPage + 9 > totalPages-1 ? totalPages-1 : startPage +9}');">Next</a></li>`;
        pagenation.append(li4);
    }
    let li5 = `<li class="page-item"><a class="page-link" href="javascript:void(0);" onclick="getQna('${totalPages -1}');">End</a></li>`;
    pagenation.append(li5);





}
