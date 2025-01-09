import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent {
  constructor(private auth:AuthService){}
}
