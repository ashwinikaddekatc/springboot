"package com.realnet." + module_name + ".controller;" + "\r\n" + 
"/*" + "\r\n" + 
" * package com.realnet.controller;" + "\r\n" + 
" * " + "\r\n" + 
" * import java.security.Principal;" + "\r\n" + 
" * " + "\r\n" + 
" * import org.springframework.beans.factory.annotation.Autowired; import" + "\r\n" + 
" * org.springframework.http.HttpStatus; import" + "\r\n" + 
" * org.springframework.http.ResponseEntity; import" + "\r\n" + 
" * org.springframework.security.authentication.AuthenticationManager; import" + "\r\n" + 
" * org.springframework.security.authentication." + "\r\n" + 
" * UsernamePasswordAuthenticationToken; import" + "\r\n" + 
" * org.springframework.security.core.Authentication; import" + "\r\n" + 
" * org.springframework.security.core.AuthenticationException; import" + "\r\n" + 
" * org.springframework.security.core.context.SecurityContextHolder; import" + "\r\n" + 
" * org.springframework.security.core.userdetails.UserDetails; import" + "\r\n" + 
" * org.springframework.web.bind.annotation.CrossOrigin; import" + "\r\n" + 
" * org.springframework.web.bind.annotation.GetMapping; import" + "\r\n" + 
" * org.springframework.web.bind.annotation.PathVariable; import" + "\r\n" + 
" * org.springframework.web.bind.annotation.PostMapping; import" + "\r\n" + 
" * org.springframework.web.bind.annotation.PutMapping; import" + "\r\n" + 
" * org.springframework.web.bind.annotation.RequestBody; import" + "\r\n" + 
" * org.springframework.web.bind.annotation.RestController;" + "\r\n" + 
" * " + "\r\n" + 
" * import com.realnet.fnd.model.User; import" + "\r\n" + 
" * com.realnet.security.JWT.JwtProvider; import" + "\r\n" + 
" * com.realnet.security.JWT.request.LoginForm; import" + "\r\n" + 
" * com.realnet.security.JWT.response.JwtResponse; import" + "\r\n" + 
" * com.realnet.service.UserService;" + "\r\n" + 
" * " + "\r\n" + 
" */" + "\r\n" + 
"		/*" + "\r\n" + 
"		 * @CrossOrigin" + "\r\n" + 
"		 * " + "\r\n" + 
"		 * @RestController public class UserController {" + "\r\n" + 
"		 * " + "\r\n" + 
"		 * @Autowired UserService userService;" + "\r\n" + 
"		 * " + "\r\n" + 
"		 * " + "\r\n" + 
"		 * @Autowired JwtProvider jwtProvider;" + "\r\n" + 
"		 * " + "\r\n" + 
"		 * @Autowired AuthenticationManager authenticationManager;" + "\r\n" + 
"		 * " + "\r\n" + 
"		 * @PostMapping(\"/login\") public ResponseEntity<JwtResponse> login(@RequestBody" + "\r\n" + 
"		 * LoginForm loginForm) { // throws Exception if authentication failed" + "\r\n" + 
"		 * " + "\r\n" + 
"		 * try { Authentication authentication = authenticationManager.authenticate( new" + "\r\n" + 
"		 * UsernamePasswordAuthenticationToken(loginForm.getUsername()," + "\r\n" + 
"		 * loginForm.getPassword()));" + "\r\n" + 
"		 * " + "\r\n" + 
"		 * SecurityContextHolder.getContext().setAuthentication(authentication);" + "\r\n" + 
"		 * " + "\r\n" + 
"		 * String jwt = jwtProvider.generate(authentication);" + "\r\n" + 
"		 * " + "\r\n" + 
"		 * UserDetails userDetails = (UserDetails) authentication.getPrincipal(); User" + "\r\n" + 
"		 * user = userService.findOne(userDetails.getUsername());" + "\r\n" + 
"		 * " + "\r\n" + 
"		 * return ResponseEntity.ok(new JwtResponse(jwt, user.getEmail()," + "\r\n" + 
"		 * user.getName(), user.getRole())); } catch (AuthenticationException e) {" + "\r\n" + 
"		 * return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); } }" + "\r\n" + 
"		 * " + "\r\n" + 
"		 * " + "\r\n" + 
"		 * @PostMapping(\"/register\") public ResponseEntity<User> save(@RequestBody User" + "\r\n" + 
"		 * user) { try { return ResponseEntity.ok(userService.save(user)); } catch" + "\r\n" + 
"		 * (Exception e) { return ResponseEntity.badRequest().build(); } }" + "\r\n" + 
"		 * " + "\r\n" + 
"		 * @PutMapping(\"/profile\") public ResponseEntity<User> update(@RequestBody User" + "\r\n" + 
"		 * user, Principal principal) {" + "\r\n" + 
"		 * " + "\r\n" + 
"		 * try { if (!principal.getName().equals(user.getEmail())) throw new" + "\r\n" + 
"		 * IllegalArgumentException(); return" + "\r\n" + 
"		 * ResponseEntity.ok(userService.update(user)); } catch (Exception e) { return" + "\r\n" + 
"		 * ResponseEntity.badRequest().build(); } }" + "\r\n" + 
"		 * " + "\r\n" + 
"		 * @GetMapping(\"/profile/{email}\") public ResponseEntity<User>" + "\r\n" + 
"		 * getProfile(@PathVariable(\"email\") String email, Principal principal) { if" + "\r\n" + 
"		 * (principal.getName().equals(email)) { return" + "\r\n" + 
"		 * ResponseEntity.ok(userService.findOne(email)); } else { return" + "\r\n" + 
"		 * ResponseEntity.badRequest().build(); }" + "\r\n" + 
"		 * " + "\r\n" + 
"		 * } }" + "\r\n" + 
"		 */" 
