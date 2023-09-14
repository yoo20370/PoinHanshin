 Dropzone.autoDiscover = false; // deprecated 된 옵션. false로 해놓는걸 공식문서에서 명시


const dropzone = new Dropzone('#my-dropzone', {
   url: "@{/protectboard/write}", // 파일을 업로드할 서버 주소 url.
   method: 'post', // 기본 post로 request 감. put으로도 할수있음

   autoProcessQueue: false, // 자동으로 보내기. true : 파일 업로드 되자마자 서버로 요청, false : 서버에는 올라가지 않은 상태. 따로 this.processQueue() 호출시 전송
   clickable: true, // 클릭 가능 여부
   autoQueue: false, // 드래그 드랍 후 바로 서버로 전송
   createImageThumbnails: true, //파일 업로드 썸네일 생성

   thumbnailHeight: 120, // Upload icon size
   thumbnailWidth: 120, // Upload icon size

   maxFiles: 10, // 업로드 파일수
   maxFilesize: 10, // 최대업로드용량 : 100MB
   paramName: "protectboardFile", // 서버에서 사용할 formdata 이름 설정 (default는 file)
   parallelUploads: 1, // 동시파일업로드 수(이걸 지정한 수 만큼 여러파일을 한번에 넘긴다.)
   uploadMultiple: false, // 다중업로드 기능
   timeout: 300000, //커넥션 타임아웃 설정 -> 데이터가 클 경우 꼭 넉넉히 설정해주자

   addRemoveLinks: true, // 업로드 후 파일 삭제버튼 표시 여부
   dictRemoveFile: '삭제', // 삭제버튼 표시 텍스트
   acceptedFiles: '.jpeg,.jpg,.png,.gif,.JPEG,.JPG,.PNG,.GIF', // 이미지 파일 포맷만 허용


   init: function () {
      // 최초 dropzone 설정시 init을 통해 호출
      console.log(file);
      var ajaxdata = {"protectboardno":$('input[name=protectboardno]').val()};


      console.log('최초 실행');
      let myDropzone = this; // closure 변수 (화살표 함수 쓰지않게 주의)
         //기존에 업로드된 서버파일이 있는 경우,
         if(mode == 'MODIFY'){
                console.log('getajax 실행');
                    $.ajax({
                         url: '/protectboard/file',
                         type: 'GET',
                         data: ajaxdata,
                         dataType : 'json',
                         success: function(response){
                              console.log(response);
                             $.each(response, function(key,value) {
                                 var mockFile = {
                                 name: value.original_file_name ,
                                 code: value.protectboardfileno ,
                                 path: `/upload/${value.stored_file_name}`};//이미지 파일경로

                                 myDropzone.emit("addedfile", mockFile);
                                 myDropzone.emit("thumbnail", mockFile, mockFile.path);
                                 myDropzone.emit("complete", mockFile);
                                 myDropzone.protectboardFile.push(mockFile);
                             });

                         }
                     });

         };


          // autoProcessQueue: false로 해주었기 때문에, 메소드 api로 파일을 서버로 제출
      },

      removedfile: function(file) {
            let myDropzone = this;
          // 파일 삭제 시
           var code = file.code == undefined ? file.temp : file.code; // 파일 업로드시 return 받은 code값
          console.log('삭제');
            $.ajax({
                type: 'DELETE',
                url: '/protectboard/file/', // 파일 삭제할 url
                data: {protectboardfileno: code},
                success: function(data) {
                    console.log('success: ' + data);
                }
            });
          var _ref;
        return (_ref = file.previewElement) != null ? _ref.parentNode.removeChild(file.previewElement) : void 0;
        myDropzone.processQueue();
      }

   });

document.getElementById('writeBtn').onclick = function() {
  const dropzone = document.getElementById('my-dropzone').dropzone;
  dropzone.processQueue();
};