<h1 align="center">
Stockbit Test
</h1>
Ini adalah projek online test dari Stockbit dengan membuat dua tampilan, yaitu Halaman Login dan Halaman Watchlist. 
</br>- Halaman Login berisi tampilan sesuai dengan UI Login pada aplikasi Stockbit.
</br>- Halaman Watchlist berisi tampilan data crypto yang diambil melalui REST API dan ditampilkan setiap 50 data setiap scroll.

## Screenshots
<p align="center">
  <img src="screenshots/1.png" width="200" alt="Login">
  <img src="screenshots/2.png" width="200" alt="Watchlist">
  <img src="screenshots/3.png" width="200" alt="Empty and Error State">
  <img src="screenshots/4.png" width="200" alt="Empty and Error State Pagination">
</p>

## Features
- [x] Toggle hide/show password
- [x] Display data from REST API every 50 data
- [x] Handling loading and error state when first request 
- [x] Pagination every 50 data
- [x] Handling loading and error state below recycler view using LoadStateAdadpter from Paging 3 Library.
- [x] Handling orientation change without request data again.

## Tech Stack
- [x] View Binding
- [x] Navigation Component
- [x] Paging 3
- [x] MVVM
- [x] Coroutine
- [x] Koin Dependency Injection

## Author
| [<img src="https://avatars1.githubusercontent.com/u/32356015?v=3" width="100px;"/><br /><sub><b>Alvin Tandiardi</b></sub>](https://github.com/alvintan05)<br /> |
| :-----------------------------------------------------------------------------------------------------------------------------------------------------------------: |
