// trades/swaps/swaps-list.component.ts
@Component({ standalone:true, template:`<div class="p-6"><h1 class="text-xl font-semibold">Swaps</h1>
<button (click)="load()">Load</button><pre>{{data | json}}</pre></div>`})
export class SwapsListComponent {
  data:any; async load(){ this.data = await fetch('/swaps').then(r=>r.json()); }
}
