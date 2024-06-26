import { Client }  from './../../entities/client';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { ClientService } from 'src/app/services/client.service';

// Import SweetAlert
import Swal from 'sweetalert2';

@Component({
  selector: 'app-client-details',
  templateUrl: './client-details.component.html',
  styleUrls: ['./client-details.component.css']
})
export class ClientDetailsComponent implements OnInit {
  constructor(private fb: FormBuilder,private clientService: ClientService, private router: Router,private httpClient:HttpClient,private route: ActivatedRoute) { }

  c: Client = new Client();
  
  number: number = 0;
  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const id = params['id'];
      // Use the extracted ID as needed
      console.log(id);
    }); 
    this.getOneClient(this.route.snapshot.params['id']);
  }
  getOneClient(id: string) {
    this.clientService.getOneClient(id).subscribe((client) => {
      console.log(client);
      this.c = client;
    });
  }

  getStarRange(count: number): number[] {
    return Array(count).fill(0).map((_, index) => index + 1);
  }
  
  makeOrder() {
    if (!this.c) {
      // Afficher la pop-up si le client n'est pas trouvé
      Swal.fire({
        icon: 'error',
        title: 'Client non trouvé',
        text: 'Aucun client trouvé avec ce CIN',
      });
      return;
    }
    // Autre logique pour créer la commande
  }
}
