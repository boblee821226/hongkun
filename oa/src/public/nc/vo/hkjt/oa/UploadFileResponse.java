package nc.vo.hkjt.oa;

import java.util.Map;

public class UploadFileResponse {
/**
 * {
    "data": {
        "fileExtendName": "txt",
        "filename": "≤‚ ‘.txt",
        "isImg": "",
        "filelink": "/spa/document/index2file.jsp?imagefileId=4630#/main/document/fileView",
        "uploaddate": "2020-11-02 19:46:06",
        "showDelete": "false",
        "showLoad": "true",
        "acclink": "/weaver/weaver.file.FileDownload?fileid=a7599209d9e63b5cc4bf596b965a34253bca010710ad7ffd04ade51ab54cdabf908d7713e8b8cabe4097669464b68df8fc20fd0036c7ce580",
        "filesize": "501B",
        "loadlink": "/weaver/weaver.file.FileDownload?fileid=a7599209d9e63b5cc4bf596b965a34253bca010710ad7ffd04ade51ab54cdabf908d7713e8b8cabe4097669464b68df8fc20fd0036c7ce580&download=1",
        "imgSrc": "/weaver/weaver.file.FileDownload?fileid=a7599209d9e63b5cc4bf596b965a34253bca010710ad7ffd04ade51ab54cdabf908d7713e8b8cabe4097669464b68df8fc20fd0036c7ce580",
        "fileid": 4630
    },
    "status": 1
}
 */

	private String status;
	private String error;
	private Map<String,Object> data;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}

}
