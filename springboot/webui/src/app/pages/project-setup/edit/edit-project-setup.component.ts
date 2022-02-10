import { Component, OnInit, ViewEncapsulation } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { ActiveTechnology } from "src/app/models/ActiveTechnology";
import { ProjectSetup } from "src/app/models/Project_setup";
import { ValidationError } from "src/app/models/ValidationError";
import { ProjectSetupService } from "src/app/services/api/project-setup.service";
import { TechnologyStackService } from "src/app/services/api/technology-stack.service";

@Component({
  selector: "app-edit-project-setup",
  templateUrl: "./edit-project-setup.component.html",
  styleUrls: ["./edit-project-setup.component.scss"],
  encapsulation: ViewEncapsulation.Emulated,
})
export class EditProjectSetupComponent implements OnInit {
  updated = false;
  project: ProjectSetup;
  id: number;

  // tech_stacks = [
  //   "SpringMVC-Hibernate-Mysql",
  //   "Angular-SpringBoot-Mysql",
  //   "React-ReactNative-Mysql",
  //   "React-ReactNative-MongoDB",
  //   "Angular-SpringBoot-MongoDB",
  //   "Php-Laravel-Mysql",
  //   "MEAN",
  // ];

  tech_stacks=[];

  errorFields: ValidationError[] = []; // backend validation field error message

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private projectSetupService: ProjectSetupService,
    private technologyStackService: TechnologyStackService
  ) {}

  ngOnInit() {
    this.project = new ProjectSetup();
    this.id = this.route.snapshot.params["id"];
    console.log("update with id = ", this.id);
    this.getById(this.id);
  
     //for dynamic tech stack
     this.technologyStackService.getAll().subscribe((data)=>{
      console.log(data)
      
      for(let ts of data.items)
      {
        if(ts.tech_stack==null)
        {
          return;
        }
        console.warn(ts.tech_stack);
       
        this.tech_stacks.push(ts.tech_stack)
        
      }

    });
  
  }

  getById(id: number) {
    this.projectSetupService.getById(id).subscribe(
      (data) => {
        this.project = data;
      },
      (err) => {
        console.log(err);
      }
    );
  }
  update() {
    this.projectSetupService.update(this.id, this.project).subscribe(
      (data) => {
        console.log(data);
        this.router.navigate(["../../all"], { relativeTo: this.route });
      }
    );
    this.project = new ProjectSetup();
  }

  onSubmit() {
    this.updated = true;
    this.update();
  }

  activeTechnologyDropDown: ActiveTechnology[];
  activeTechnologyDropdown() {
    this.technologyStackService.getActiveTechnology().subscribe((data) => {
      console.log(data);
      this.activeTechnologyDropDown = data;
    });
  }

  back() {
    this.router.navigate(["../../all"], { relativeTo: this.route });
  }
}
