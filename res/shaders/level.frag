varying vec3 color;

uniform sampler2D texture1;

void main() {
    vec4 textureColor = texture2D(texture1, gl_TexCoord[0].st);	

	if (textureColor.w == 0)
		discard;

    gl_FragColor = textureColor * vec4(color, 1);
}