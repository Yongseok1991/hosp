window.addEventListener("keydown", (e) => {
    if(e.key == 'Enter') {
        document.querySelector("#btn-submit").click();
    }
})


document.querySelector("#btn-submit").addEventListener("click", (e) => {
    let sigunNm = document.querySelector("#sigunNm").value;
    let cmpnmNm = document.querySelector("#cmpnmNm").value;
    if(cmpnmNm == '' || cmpnmNm == 'undefined') {
        alert("가게를 입력해주세요");
        return false;
    }
    getKyonggi(sigunNm, cmpnmNm, 0);
})
let getKyonggi = async (sigunNm, cmpnmNm, page) => {
    let response = await fetch(`/api/kyonggi?sigunNm=${sigunNm}&cmpnmNm=${cmpnmNm}&page=${page}`);
    let responsePasing = await response.json();
    setKyonggi(responsePasing);
}

let setKyonggi = (responsePasing) => {
    let tbodyKyonggiDom = document.querySelector("#tbody-kyonggi");
    tbodyKyonggiDom.innerHTML = '';
    document.querySelector("#amount").innerHTML = `총 검색 결과 ${responsePasing.totalElements}개`;
    let pagenation = $("#pagination");
    pagenation.empty();
    if(responsePasing.content.length == 0) {
        let trEL = document.createElement("tr");
        let tdEL1 = document.createElement("td");
        tdEL1.colSpan = 3;
        tdEL1.innerHTML = "찾으시는 가게가 없습니다.";
        tdEL1.style.textAlign = "center"
        trEL.append(tdEL1);
        tbodyKyonggiDom.append(trEL);
    } else {
        responsePasing.content.forEach((e) => {
            let trEL = document.createElement("tr");
            let tdEL1 = document.createElement("td");
            let tdEL2 = document.createElement("td");
            let tdEL3 = document.createElement("td");

            tdEL1.innerHTML = e.cmpnmNm;
            tdEL2.innerHTML = e.indutypeNm;
            tdEL3.innerHTML = e.refineLotnoAddr === 'null' ? '' : e.refineLotnoAddr;

            trEL.append(tdEL1);
            trEL.append(tdEL2);
            trEL.append(tdEL3);

            tbodyKyonggiDom.append(trEL);
        })

        let pageNumber = responsePasing.pageable.pageNumber;
        let pageSize = responsePasing.pageable.pageSize;
        let totalPages = responsePasing.totalPages;
        let startPage = Math.floor(pageNumber/pageSize) * pageSize +1;
        let endPage = startPage + pageSize -1;
        endPage = totalPages < endPage ? totalPages : endPage;

        let sigunNm = document.querySelector("#sigunNm").value;
        let cmpnmNm = document.querySelector("#cmpnmNm").value;

        var li0 = `<li class="page-item"><a class="page-link" href="javascript:void(0);" onclick="getKyonggi('${sigunNm}', '${cmpnmNm}', 0);">Start</a></li>`;
        pagenation.append(li0);

        if(responsePasing.first == false) {
            var li1 = `<li class="page-item"><a class="page-link" href="javascript:void(0);" onclick="getKyonggi('${sigunNm}', '${cmpnmNm}', '${startPage -11 > 0 ? startPage -11 : 0}');">previous</a></li>`;
            pagenation.append(li1);
        }
        for(let i= startPage; i<=endPage; i++) {
            if(pageNumber+1 == i) {
                var li2 = `<li class="page-item active"><a class="page-link" href="javascript:void(0);" onclick="getKyonggi('${sigunNm}', '${cmpnmNm}', '${i - 1}');">${i}</a></li>`;
                pagenation.append(li2);
            } else {
                var li3 = `<li class="page-item"><a class="page-link" href="javascript:void(0);" onclick="getKyonggi('${sigunNm}', '${cmpnmNm}', '${i - 1}');">${i}</a></li>`;
                pagenation.append(li3);
            }
        }

        if(responsePasing.last == false) {
            var li4 = `<li class="page-item"><a class="page-link" href="javascript:void(0);" onclick="getKyonggi('${sigunNm}', '${cmpnmNm}', '${startPage + 9 > totalPages-1 ? totalPages-1 : startPage +9}');">Next</a></li>`;
            pagenation.append(li4);
        }
        var li5 = `<li class="page-item"><a class="page-link" href="javascript:void(0);" onclick="getKyonggi('${sigunNm}', '${cmpnmNm}', '${totalPages -1}');">End</a></li>`;
        pagenation.append(li5);
    }
}