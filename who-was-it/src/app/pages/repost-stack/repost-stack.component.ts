import { Component, Input } from '@angular/core';
import { DetallesPost } from '../../models/detalles-post';

@Component({
  selector: 'app-repost-stack',
  templateUrl: './repost-stack.component.html',
  styleUrl: './repost-stack.component.css'
})
export class RepostStackComponent {
  @Input() repost!: DetallesPost | null; // Repost actual

}
