varying vec3 color;

void main()
{
	color = gl_Color.rgb;
    gl_Position = ftransform();
    gl_TexCoord[0] = gl_MultiTexCoord0;
}
