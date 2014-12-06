#version 120

varying vec2 texCoord0;

uniform vec3 ambientIntensity;
uniform sampler2D sampler;

void main()
{
	gl_FragColor = texture2D(sampler, vec2(texCoord0.x, 1 - texCoord0.y)) * vec4(ambientIntensity, 1);
}