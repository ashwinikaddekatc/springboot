import { Component, OnInit, ViewEncapsulation } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { FileData } from "src/app/models/FileData";
import { FileList } from "src/app/models/FileList";
import { CodeExtractionService } from "src/app/services/api/code-extraction.service";

@Component({
  selector: "app-file-editor",
  templateUrl: "./file-editor.component.html",
  styleUrls: ["./file-editor.component.scss"],
  encapsulation: ViewEncapsulation.Emulated,
})
export class FileEditorComponent implements OnInit {
  codeMirrorOptions: any = {
    theme: "lint",
    mode: "text/x-java",
    lineNumbers: true,
    //noNewlines: true,
    lineSeparator: "\n",
    lineWrapping: true,
    foldGutter: true,
    gutters: [
      "CodeMirror-linenumbers",
      "CodeMirror-foldgutter",
      "CodeMirror-lint-markers",
    ],
    autoCloseBrackets: true,
    extraKeys: { "Ctrl-Space": "autocomplete" },
    matchBrackets: true,
    lint: true,
  };

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private codeExtractionService: CodeExtractionService
  ) {}

  headerId: number;
  fileList: FileList[]; // for dropdown
  //fileData: FileData = new FileData();
  fileData: FileData = {} as FileData;
  code: string = '';
  ngOnInit() {
    this.code = "// edit your code here";
    
    this.route.queryParams.subscribe((params) => {
      this.headerId = +params["header_id"];
    });
    this.getFileListDropDownData(this.headerId);
    //this.fileData.text = "//edit your files here";
  }

  // dropdown data
  getFileListDropDownData(id: number) {
    this.codeExtractionService.getFileList(id).subscribe(
      (data) => {
        console.log(data);
        this.fileList = data;
      },
      (err) => {
        console.log(err);
      }
    );
  }




  readFile(event) {
    console.log(event.target.value);
    let id = event.target.value;
    //this.codeExtractionService.readFile(id).subscribe(
    this.codeExtractionService.readStaticFile(id).subscribe(
      (res) => {
        console.log(res);
        //this.fileData = new FileData();
        this.fileData = res;
        //this.code = this.fileData.text;
      },
      (err) => {
        console.log(err);
      }
    );
    //this.fileData = new FileData();
  }

  /* readStaticFile(id: number) {
    this.codeExtractionService.readStaticFile(id).subscribe(
      (res) => {
        console.log(res);
        this.fileData = res;
      },
      (err) => {
        console.log(err);
      }
    );
  } */
  
  saveTextIntoFile() {
    //this.codeExtractionService.saveCodeIntoFile(this.fileData).subscribe(
    this.codeExtractionService.saveCodeIntoStaticFile(this.fileData).subscribe(
      (res) => {
        console.log(res);
      },
      (err) => {
        console.log(err);
      }
    );
  }

  /* saveTextIntoStaticFile() {
    this.codeExtractionService.saveCodeIntoStaticFile(this.fileData).subscribe(
      (res) => {
        console.log(res);
      },
      (err) => {
        console.log(err);
      }
    );
  } */
}
